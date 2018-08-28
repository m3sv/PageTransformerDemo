# Implementing ViewPager.PageTransformer with multiple fragments

## Create MainActivity and inflate fragment with ViewPager

```Kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ViewPagerFragment())
                .commit()
    }
```

## Define ViewDelegate and ViewElement

```Kotlin
interface ViewDelegate {
    fun onScroll(position: Float, tag: Int?)

    fun addListener(tag: Int?, viewElement: ViewElement)
}
```

```Kotlin
interface ViewElement {
    fun onScroll(position: Float)
}
```

ViewDelegate(fragment containing ViewPager) will be used as a link between ViewElements(fragments that live inside ViewPager)

## Create ViewPagerDeletationFragment that will abstract away ViewDelegate details, we will make it abstract so that client won't be able to instantiate it by mistake

```Kotlin
abstract class ViewPagerDelegationFragment : Fragment(), ViewDelegate {

    private val listeners: MutableMap<Int, WeakReference<ViewElement>> = mutableMapOf()

    override fun onScroll(position: Float, tag: Int?) {
        tag?.let { listeners[it]?.get()?.onScroll(position) }
    }

    override fun addListener(tag: Int?, viewElement: ViewElement) {
        tag?.let { listeners[it] = WeakReference(viewElement) }
    }
}
```

## Create ViewPagerFragment that will inherit from ViewPagerDeletationFragment 

```Kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<ViewPager>(R.id.view_pager).also {
        it.offscreenPageLimit = NUM_PAGES - 1
        it.adapter = PagerAdapter(childFragmentManager)
        it.setPageTransformer(true, ViewPagerTransformer(this))
    }
}
```

We set our PageTransformer, which we will define later, in onViewCreated and give it our ViewPagerDelegationFragment as a parameter.

## Create custom ViewPagerTransformer and extend ViewPager.PageTransformer

```Kotlin
class ViewPagerTransformer(viewDelegate: ViewDelegate) : ViewPager.PageTransformer,
    ViewDelegate by viewDelegate {
    override fun transformPage(view: View, position: Float) {
        onScroll(position, view.tag as? Int)
    }
}
```

ViewPagerTransformer takes our ViewDelegate(fragment that contains ViewPager) as a parameter and we call onScroll when transformPage is called. 

We need to pass view.tag with position, because we need to differentiate between ViewPager pages in order to invoke onScroll on a specific fragment.

## Create ViewPagerPageFragment 

For simplicity, I used one fragment that has different content when created with different Int value. 

The most crucial part is implementing ViewElement interface, setting view tag in onCreateView and adding listener in onViewCreated.

**Don't forget to remove listener in onDestroyView.**

Then you can modify your view position in onScroll.



