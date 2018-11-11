Lib-I18n
===

[![Build Status](https://travis-ci.org/Naoghuman/lib-i18n.svg?branch=master)](https://travis-ci.org/Naoghuman/lib-i18n)
[![license: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub release](https://img.shields.io/github/release/Naoghuman/lib-i18n.svg)](https://GitHub.com/Naoghuman/lib-i18n/releases/)



Intention
---

The library `Lib-I18N` allowed the developer to bind easly `.properties` key 
(values) to a [StringBinding] or [Callable&lt;String&gt;]. So changing the 
language during runtime in a [JavaFX] application won't be a problem anymore.  
Lib-I18N is written in JavaFX, [Maven] and [NetBeans].



Content
---

* [Examples](#Examples)
    - [How to use the builder 'I18NResourceBundleBuilder'](#HoToUsReBuBu)
    - [How to use the builder 'I18NBindingBuilder'](#HoToUsBiBu)
    - [How to use the builder 'I18NResourceBundleMessageBuilder'](#HoToUsReBuBuMe)
* [API](#API)
    - [com.github.naoghuman.lib.i18n.core.I18NBinding](#I18nBi)
    - [com.github.naoghuman.lib.i18n.core.I18NBindingBuilder](#I18nBiBu)
    - [com.github.naoghuman.lib.i18n.core.I18NFacade](#I18nFa)
    - [com.github.naoghuman.lib.i18n.core.I18NResourceBundle](#I18nReBu)
    - [com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder](#I18nReBuBu)
    - [com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder](#I18nReBuMeBu)
* [Download](#Download)
* [Requirements](#Requirements)
* [Installation](#Installation)
* [Documentation](#Documentation)
* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



Examples<a name="Examples" />
---

### How to use the builder 'I18NResourceBundleBuilder'<a name="HoToUsReBuBu" />

With the builder [I18NResourceBundleBuilder] the developer can configure the 
[ResourceBundle] which contains the `key - value` terms which will then be bind 
to a [Locale].
```java
I18NResourceBundleBuilder.configure()
        .baseName(String)
        .defaultLocale(Locale)
        .actualLocale(Locale)
        .supportedLocales(Locale...)
        .build()
```

### How to use the builder 'I18NBindingBuilder'<a name="HoToUsBiBu" />

The builder [I18NBindingBuilder] let the developer create a [StringBinding] with 
a function from type [Callable&lt;String&gt;] or a .properties `key` with optional 
`arguments`.
```java
I18NBindingBuilder.bind()
       .callable(Callable<String>)
       .build()

I18NBindingBuilder.bind()
       .key(String)
       .arguments(Object... args) // optional
       .build()
```

### How to use the builder 'I18NResourceBundleMessageBuilder'<a name="HoToUsReBuBuMe" />

To load a .properties `key` with optional `arguments` from the predefined [ResourceBundle] 
in [I18NResourceBundleBuilder] the developer can use the builder [I18NResourceBundleMessageBuilder].
```java
    I18NResourceBundleMessageBuilder.getString()
           .key(String)
           .arguments(Object...) // optional
           .build()
```



API<a name="API" />
---

### com.github.naoghuman.lib.i18n.core.I18NBinding<a name="I18nBi" />

```java
/**
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface I18NBinding
```

```java
/**
 * 
 * @param  function
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public StringBinding createStringBinding(final Callable<String> function);
```

```java
/**
 * 
 * @param  key
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public StringBinding createStringBinding(final String key);
```

```java
/**
 * 
 * @param  key
 * @param  arguments
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public StringBinding createStringBinding(final String key, final Object... arguments);
```

### com.github.naoghuman.lib.i18n.core.I18NBindingBuilder<a name="I18nBiBu" />

```java
/**
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public final class I18NBindingBuilder
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public static final FirstStep bind()
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface FirstStep {
    
    /**
     * 
     * @param  callable
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public LastStep callable(final Callable<String> callable);
    
    /**
     * 
     * @param  key
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public SecondStep key(final String key);
    
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface SecondStep {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public Optional<StringBinding> build();
    
    /**
     * 
     * @param  arguments
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public LastStep arguments(final Object... arguments);
    
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface LastStep {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public Optional<StringBinding> build();
    
}
```

### com.github.naoghuman.lib.i18n.core.I18NFacade<a name="I18nFa" />

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public final class I18NFacade implements I18NBinding, I18NResourceBundle
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public static I18NFacade getDefault()
```

### com.github.naoghuman.lib.i18n.core.I18NResourceBundle<a name="I18nReBu" />

```java
/**
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface I18NResourceBundle
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public String getBaseName();
```

```java
/**
 * 
 * @param  baseName 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public void setBaseName(final String baseName);
```

```java
/**
 * 
 * @param  key
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public String getString(final String key);
```

```java
/**
 * 
 * @param  key
 * @param  arguments
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public String getString(final String key, final Object... arguments);
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public Locale getDefaultLocale();
```

```java
/**
 * 
 * @param  locale 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public void setDefaultLocale(final Locale locale);
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public Locale getActualLocale();
```

```java
/**
 * 
 * @param  locale 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public void setActualLocale(final Locale locale);
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public ObjectProperty<Locale> actualLocaleProperty();
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public ObservableList<Locale> getSupportedLocales();
```

```java
/**
 * 
 * @param  locales 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public void setSupportedLocales(final ObservableList<Locale> locales);
```

### com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder<a name="I18nReBuBu" />

```java
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NResourceBundleBuilder
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public static final FirstStep configure()
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface FirstStep {
    
    /**
     * 
     * @param  baseName
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public SecondStep baseName(final String baseName);
    
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface SecondStep {
    
    /**
     * 
     * @param  locales
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public ThirdStep supportedLocales(final ObservableList<Locale> locales);

}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface ThirdStep {
    
    /**
     * 
     * @param  locale
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public ForthStep defaultLocale(final Locale locale);
   
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface ForthStep {
    
    /**
     * 
     * @param  locale
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public LastStep actualLocale(final Locale locale);
    
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface LastStep {
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public void build();
    
}
```

### com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder<a name="I18nReBuMeBu" />

```java
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NResourceBundleMessageBuilder
```

```java
/**
 * 
 * @return 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public static final FirstStep getString()
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface FirstStep {
    
    /**
     * 
     * @param  key
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public SecondStep key(final String key);
    
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface SecondStep {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public String build();
    
    /**
     * 
     * @param  arguments
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public LastStep arguments(final Object... arguments);
    
}
```

```java
/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface LastStep {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public String build();
   
}
```



Download<a name="Download" />
---

TODO

**Maven coordinates**  
In context from a [Maven] project you can use following maven coordinates: 
```xml
TODO
```

Download:
* TODO

An overview about all existings releases can be found here:
* [Overview] from all releases in `Lib-I18N`.



Requirements<a name="Requirements" />
---

* On your system you need [JRE 8] or [JDK 8] installed.
* TODO

In the library are following libraries registered as dependencies:
* The library [lib-logger-0.6.0.jar](#Installation).
  * Included in `Lib-Logger` is the library [log4j-api-2.10.0.jar].
  * Included is `Lib-Logger` is the library [log4j-core-2.10.0.jar].



Installation<a name="Installation" />
---

##### Install the project in your preferred IDE

* If not installed download the [JRE 8] or the [JDK 8].
    - Optional: To work better with [FXML] files in a [JavaFX] application 
      download the JavaFX [Scene Builder] supported by `Gluon`.
* Choose your preferred IDE (e.g. [NetBeans], [Eclipse] or [IntelliJ IDEA]) for development.
* Download or clone [Lib-Logger].
* Open the projects in your IDE and run them.



Documentation<a name="Documentation" />
---

* In section [Api](#Api) you can see the main point(s) to access the functionalities 
  in this library.
* For additional information see the [JavaDoc] in the library itself.



Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you could report an [Issue].
* If you want to contribute to the project plz fork the project and do a [Pull Request].



License<a name="License" />
---

The project `Lib-I18n` is licensed under [General Public License 3.0].



Autor<a name="Autor" />
---

The project `Lib-I18n` is maintained by me, Peter Rogge. See [Contact](#Contact).



Contact<a name="Contact" />
---

You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Images)



[//]: # (Links)
[Callable&lt;String&gt;]:https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/Callable.html
[Eclipse]:https://www.eclipse.org/
[FXML]:http://docs.oracle.com/javafx/2/fxml_get_started/jfxpub-fxml_get_started.htm
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[I18NBindingBuilder]:https://github.com/Naoghuman/lib-i18n/blob/master/src/main/java/com/github/naoghuman/lib/i18n/core/I18NBindingBuilder.java
[I18NResourceBundleBuilder]:https://github.com/Naoghuman/lib-i18n/blob/master/src/main/java/com/github/naoghuman/lib/i18n/core/I18NResourceBundleBuilder.java
[I18NResourceBundleMessageBuilder]:https://github.com/Naoghuman/lib-i18n/blob/master/src/main/java/com/github/naoghuman/lib/i18n/core/I18NResourceBundleMessageBuilder.java
[IntelliJ IDEA]:http://www.jetbrains.com/idea/
[Issue]:https://github.com/Naoghuman/lib-i18n/issues
[JavaDoc]:http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[JDK 8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JRE 8]:http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[Lib-Logger]:https://github.com/Naoghuman/lib-logger
[Locale]:https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html
[log4j-api-2.10.0.jar]:https://logging.apache.org/log4j/2.0/log4j-web/dependencies.html
[log4j-core-2.10.0.jar]:https://logging.apache.org/log4j/2.0/log4j-web/dependencies.html
[Maven]:http://maven.apache.org/
[NetBeans]:https://netbeans.org/
[Overview]:https://github.com/Naoghuman/lib-i18n/releases
[Pull Request]:https://help.github.com/articles/using-pull-requests
[ResourceBundle]:https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html
[Scene Builder]:https://gluonhq.com/products/scene-builder/
[StringBinding]:https://docs.oracle.com/javase/8/javafx/api/javafx/beans/binding/StringBinding.html
[UML]:https://en.wikipedia.org/wiki/Unified_Modeling_Language
