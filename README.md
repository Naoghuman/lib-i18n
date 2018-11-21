Lib-I18n
===

[![Build Status](https://travis-ci.org/Naoghuman/lib-i18n.svg?branch=master)](https://travis-ci.org/Naoghuman/lib-i18n)
[![license: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub release](https://img.shields.io/github/release/Naoghuman/lib-i18n.svg)](https://GitHub.com/Naoghuman/lib-i18n/releases/)



Intention
---

The library `Lib-I18N` allowed the developer to bind easly `.properties` key (values) 
to a [StringBinding]. So changing the language during runtime in a [JavaFX] application 
won't be a problem anymore.  
Lib-I18N is written in JavaFX, [Maven] and [NetBeans].

A demo application which shows the features from this library can be found under 
[App-I18N-Demo] (which is currently under development).
* TODO Add screenshoot.


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
to a [Locale]. That means switching the `actual` Locale update all binded textes 
with the specific value from the corresponding language `.properties` file.
```java
/**
 * 1) Starts the configuration process.
 * 2) Defines the path and name from the .properties file.
 * 3) Sets all supported Locales.
 * 4) Sets the default Locale.
 * 5) Sets the actual Locale.
 * 6) Completes the configuration process.
 */
I18NResourceBundleBuilder.configure() // 1
        .baseName(String)             // 2
        .supportedLocales(ObservableList<Locale>) // 3
        .defaultLocale(Locale)        // 4
        .actualLocale(Locale)         // 5
        .build();                     // 6
```

### How to use the builder 'I18NBindingBuilder'<a name="HoToUsBiBu" />

The builder [I18NBindingBuilder] let the developer create a [StringBinding]. The 
StringBinding can created with a function from type [Callable&lt;String&gt;] or 
with a .properties `key` and optional `arguments`.
```java
/**
 * 1) Starts the binding process.
 * 2) Use the given function to create a StringBinding.
 * 3) Completes the binding process and returns the StringBinding.
 */
I18NBindingBuilder.bind()          // 1
       .callable(Callable<String>) // 2
       .build();                   // 3

/**
 * 1) Starts the binding process.
 * 2) Defines the key which value will be bind to the StringBinding.
 * 3) Optional arguments for the value from the given key.
 * 4) Completes the binding process and returns the StringBinding.
 */
I18NBindingBuilder.bind()         // 1
       .key(String)               // 2
       .arguments(Object... args) // 3
       .build();                  // 4
```

### How to use the builder 'I18NResourceBundleMessageBuilder'<a name="HoToUsReBuBuMe" />

To load a .properties `key` with optional `arguments` from the configured [ResourceBundle] 
throw the [I18NResourceBundleBuilder] the developer can use the builder [I18NResourceBundleMessageBuilder].
```java
/**
 * 1) Starts the message process.
 * 2) Defines the key which value will be loaded.
 * 3) Optional arguments for the value from the given key.
 * 4) Completes the message process and returns a String.
 */
I18NResourceBundleMessageBuilder.message() // 1
        .key(String)                       // 2
        .arguments(Object...)              // 3
        .build();                          // 4
```



API<a name="API" />
---

### com.github.naoghuman.lib.i18n.core.I18NBinding<a name="I18nBi" />

```java
/**
 * This {@code Interface} gives the developer the possibilities to create a 
 * {@link javafx.beans.binding.StringBinding} which is associated with a {@code key} 
 * (value) from a {@link java.util.ResourceBundle}.
 * <p>
 * To associated a {@code key} with a {@code StringBinding} the developer can use 
 * on the one side the methods which will expected directly a {@code key} (with 
 * optional {@code arguments}) or on the other side a {@link java.util.concurrent.Callable} 
 * which computes then the message.
 * <p>
 * The preferred way to used the methods from this interface is the usage from the 
 * builder {@link com.github.naoghuman.lib.i18n.core.I18NBindingBuilder}.<br>
 * An other option for advanced developers is the facade 
 * {@link com.github.naoghuman.lib.i18n.core.I18NFacade}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NBindingBuilder
 * @see     com.github.naoghuman.lib.i18n.core.I18NFacade
 * @see     java.util.ResourceBundle
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.binding.StringBinding
 */
public interface I18NBinding
```

```java
/**
 * Creates a {@link javafx.beans.binding.StringBinding} to a localized String 
 * that is computed by calling the given {@code function}.
 * <p>
 * Internal the {@code StringBinding} will be created with 
 * {@link javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...) } 
 * where the {@code Observable} is {@link com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty() }.
 * 
 * @param   function which should be used to create the {@code StringBinding}.
 * @return  the created {@code StringBinding}.
 * @throws  NullPointerException if {@code function} is NULL.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty()
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.Observable
 * @see     javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...)
 * @see     javafx.beans.binding.StringBinding
 */
public StringBinding createStringBinding(final Callable<String> function);
```

```java
/**
 * Creates a {@link javafx.beans.binding.StringBinding} to a localized String 
 * that is computed by calling the given {@code key}.
 * <p>
 * Internal the {@code StringBinding} will be created with 
 * {@link javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...) } 
 * where the {@code Observable} is {@link com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty() }.
 * 
 * @param   key which should be used to load the associated {@value}.
 * @return  the created {@code StringBinding}.
 * @throws  NullPointerException     if {@code key} is NULL.
 * @throws  IllegalArgumentException if {@code key} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty()
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.Observable
 * @see     javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...)
 * @see     javafx.beans.binding.StringBinding
 */
public StringBinding createStringBinding(final String key);
```

```java
/**
 * Creates a {@link javafx.beans.binding.StringBinding} to a localized String 
 * that is computed by calling the given {@code key} and the {@code arguments}.
 * <p>
 * Internal the {@code StringBinding} will be created with 
 * {@link javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...) } 
 * where the {@code Observable} is {@link com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty() }.
 * 
 * @param   key       which should be used to load the associated {@value}.
 * @param   arguments which should be injected into the associated {@value}.
 * @return  the created {@code StringBinding}.
 * @throws  NullPointerException     if ({@code key} || {@code arguments}) is NULL.
 * @throws  IllegalArgumentException if ({@code key} || {@code arguments}) is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty()
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.Observable
 * @see     javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...)
 * @see     javafx.beans.binding.StringBinding
 */
public StringBinding createStringBinding(final String key, final Object... arguments);
```

### com.github.naoghuman.lib.i18n.core.I18NBindingBuilder<a name="I18nBiBu" />

```java
/**
 * With the fluent builder {@code I18NBindingBuilder} the developer can easily create 
 * a {@link javafx.beans.binding.StringBinding} wrapped into a {@link java.util.Optional}.
 * <p>
 * With the builder the developer have 2 ways to create a {@code StringBinding}:
 * <ul>
 * <li>First with the usage from a function from type {@link java.util.concurrent.Callable}&lt;String&gt;,</li>
 * <li>second with the usage from a {@code key} with optional {@code arguments}.</li>
 * </ul>
 * Hint:<br>
 * The {@code value} from the given {@code key} will be loaded from the previous 
 * configured {@link java.util.ResourceBundle} through the fluent builder 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     java.util.Optional
 * @see     java.util.ResourceBundle
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.binding.StringBinding
 */
public final class I18NBindingBuilder
```

```java
/**
 * Starting point from this fluent builder to generate a {@link javafx.beans.binding.StringBinding}.
 * <p>
 * The method {@code bind()} leads to the 2 choises how the developer will create the 
 * {@code StringBinding}.
 * <ul>
 * <li>First with a function from type {@link java.util.concurrent.Callable}&lt;String&gt;,</li>
 * <li>and second with the usage from a {@code key} with optional {@code arguments}.</li>
 * </ul>
 * 
 * @return  the first step to generate a {@code StringBinding}.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.binding.StringBinding
 */
public static final FirstStep bind()
```

```java
/**
 * First mandory step to generate a {@link javafx.beans.binding.StringBinding}.
 * <p>
 * This {@code Interface} allowed the developer to choose one of the 2 choises:
 * <ul>
 * <li>First the usage from a funcation from type {@link java.util.concurrent.Callable}&lt;String&gt;,</li>
 * <li>or second the usage from a {@code key} with optional {@code arguments} which 
 *     will be injected into the {@code value}.</li>
 * </ul>
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.binding.StringBinding
 */
public interface FirstStep {

    /**
     * Setter for the developers choose to generate a {@link javafx.beans.binding.StringBinding} 
     * with a function from type {@link java.util.concurrent.Callable}&lt;String&gt;.
     * 
     * @param   function which will be used to generate a {@code StringBinding}.
     * @return  the last step in this fluent builder.
     * @throws  NullPointerException if {@code function} is NULL.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.concurrent.Callable
     * @see     javafx.beans.binding.StringBinding
     */
    public LastStep callable(final Callable<String> function);

    /**
     * Setter for the developers choose to generate a {@link javafx.beans.binding.StringBinding} 
     * with a {@code key}.
     * <p>
     * Hint:<br>
     * The {@code value} from the given {@code key} will be loaded from the previous 
     * configured {@link java.util.ResourceBundle} through the fluent builder 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
     * 
     * @param   key which {@code value} should be loaded from the {@code ResourceBundle}.
     * @return  the second step in this fluent builder.
     * @throws  NullPointerException     if {@code key} is NULL.
     * @throws  IllegalArgumentException if {@code key} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     * @see     java.util.ResourceBundle
     * @see     javafx.beans.binding.StringBinding
     */
    public SecondStep key(final String key);

}
```

```java
/**
 * Second mandory step to generate a {@link javafx.beans.binding.StringBinding} 
 * if the developer has choosen the option to create a {@code StringBinding} 
 * with a {@code key}.
 * <p>
 * This {@code Interface} allowed the developer to choose one of the 2 choises:
 * <ul>
 * <li>First use the method {@code build()} if the {@code value} doesn't need any 
 *     {@code arguments} injected.</li>
 * <li>or second use the method {@code arguments(Object...)} which will then be injected 
 *     into the {@code value}.</li>
 * </ul>
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     javafx.beans.binding.StringBinding
 */
public interface SecondStep {

    /**
     * Choose this option if for the previous defined {@code key} no addtional 
     * {@code arguments} are needed to injected into the {@code value}.
     * 
     * @return  the generated {@link javafx.beans.binding.StringBinding}.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     javafx.beans.binding.StringBinding
     */
    public Optional<StringBinding> build();

    /**
     * Choose this option if for the previsous defined {@code key} additional 
     * {@code arguments} are needed to injected into the {@code value}.
     * 
     * @param   arguments which should be injected into the {@code value}.
     * @return  the last step in this fluent builder.
     * @throws  NullPointerException     if {@code arguments} is NULL.
     * @throws  IllegalArgumentException if {@code arguments} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     */
    public LastStep arguments(final Object... arguments);

}
```

```java
/**
 * The last step in this fluent builder.
 * <p>
 * With the option {@code build()} the developer can complete the previsous 
 * definition steps and create therewith the {@link javafx.beans.binding.StringBinding}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     javafx.beans.binding.StringBinding
 */
public interface LastStep {

    /**
     * Creates the {@link javafx.beans.binding.StringBinding} with the previous 
     * defined parameters {@code key} and optional {@code arguments}.
     * 
     * @return  the generated {@code StringBinding}.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     javafx.beans.binding.StringBinding
     */
    public Optional<StringBinding> build();

}
```

### com.github.naoghuman.lib.i18n.core.I18NFacade<a name="I18nFa" />

```java
/**
 * Over the facade {@code I18NFacade} the developer have access to all methods 
 * from the interfaces {@link com.github.naoghuman.lib.i18n.core.I18NBinding} 
 * and {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle}.
 * <p>
 * The usage from the builders
 * <ul>
 * <li>{@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}: 
 *     Allowed the developer to configure the {@link java.util.ResourceBundle}.</li>
 * <li>{@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder}: 
 *     Allowed the developer to access the messages from the bundle.</li>
 * <li>{@link com.github.naoghuman.lib.i18n.core.I18NBindingBuilder}: 
 *     Allowed the developer to create a {@link javafx.beans.binding.StringBinding}.</li>
 * </ul>
 * is preferred.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NBinding
 * @see     com.github.naoghuman.lib.i18n.core.I18NBindingBuilder
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder
 * @see     java.util.ResourceBundle
 * @see     javafx.beans.binding.StringBinding
 */
public final class I18NFacade implements I18NBinding, I18NResourceBundle
```

```java
/**
 * Returns a {@code singleton} instance from this facade.
 * 
 * @return  a {@code singleton} instance from this facade.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 */
public static I18NFacade getDefault()
```

### com.github.naoghuman.lib.i18n.core.I18NResourceBundle<a name="I18nReBu" />

```java
/**
 * This {@code Interface} allowed the developer to managed following tasks:<br>
 * Configuration from the {@link java.util.ResourceBundle}:
 * <ul>
 * <li>Setting the {@code base bundle name} for the {@code ResourceBundle}.</li>
 * <li>Setting the {@code supported} {@link java.util.Locale}s.</li>
 * <li>Setting the {@code default} {@code Locale}.</li>
 * <li>and last setting the {@code actual} {@code Locale}.</li>
 * </ul>
 * Access to the {@code ResourceBundle}:
 * <ul>
 * <li>Return directly the {@code value} from a given {@code key} as {@link java.lang.String}.</li>
 * <li>or inject additional {@code arguments} into the message with 
 *     {@link java.text.MessageFormat#format(java.lang.String, java.lang.Object...)}.</li>
 * </ul>
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.lang.Object
 * @see     java.lang.String
 * @see     java.text.MessageFormat#format(java.lang.String, java.lang.Object...)
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public interface I18NResourceBundle
```

```java
/**
 * Returns the {@code baseBundleName} from the associated {@link java.util.ResourceBundle}.
 * 
 * @return  the {@code baseName} from the {@code ResourceBundle}.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.ResourceBundle
 */
public String getBaseBundleName();
```

```java
/**
 * Sets the {@code baseBundleName} which will be used to load the associated 
 * {@link java.util.ResourceBundle}.
 * <p>
 * Format from the {@code baseBundleName} should be:<br>
 * <ul>
 * <li>Package name to the bundle,  '.' (point) separated.</li>
 * <li>Base name from the bundles.</li>
 * </ul>
 * Example:
 * <ul>
 * <li>Package: com.github.naoghuman.app.i18n.demo</li>
 * <li>Bundles: message_de.properties, message_en.properties</li>
 * <li>Base bundle name: com.github.naoghuman.app.i18n.demo.message</li>
 * </ul>
 * 
 * @param   baseBundleName 
 * @throws  NullPointerException     if {@code baseBundleName} is NULL.
 * @throws  IllegalArgumentException if {@code baseBundleName} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 */
public void setBaseBundleName(final String baseBundleName);
```

```java
/**
 * Returns the associated {@code value} from the given {@code key} depending 
 * from the {@code actual} {@link java.util.Locale}.
 * 
 * @param   key which {@code value} should be loaded.
 * @return  the associated {@code value}.
 * @throws  NullPointerException     if {@code key} is NULL.
 * @throws  IllegalArgumentException if {@code key} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     java.util.Locale
 */
public String getMessage(final String key);
```

```java
/**
 * Returns the associated {@code value} (with the injected {@code arguments}) 
 * from the given {@code key} depending from the {@code actual} {@link java.util.Locale}.
 * <p>
 * Internal {@link java.text.MessageFormat#format(java.lang.String, java.lang.Object...) } 
 * will be used to format the {@code message}.
 * 
 * @param   key which {@code value} should be loaded.
 * @param   arguments  which should be injected into the associated {@value}.
 * @return  the associated {@code value}.
 * @throws  NullPointerException     if ({@code key} || {@code arguments}) is NULL.
 * @throws  IllegalArgumentException if ({@code key} || {@code arguments}) is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     java.text.MessageFormat#format(java.lang.String, java.lang.Object...)
 * @see     java.util.Locale
 */
public String getMessage(final String key, final Object... arguments);
```

```java
/**
 * Returns the {@code default} {@link java.util.Locale}.
 * 
 * @return  the {@code default} {@code Locale}.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 */
public Locale getDefaultLocale();
```

```java
/**
 * Sets the {@code default} {@link java.util.Locale}.
 * <p>
 * If the {@code supported} Locales doesn't contained the {@code Locale} then 
 * {@link java.util.Locale#ENGLISH} will be used instead.
 * 
 * @param   locale the new {@code default} {@code Locale}.
 * @throws  NullPointerException     if {@code locale} is NULL.
 * @throws  IllegalArgumentException if {@code locale} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setSupportedLocales(javafx.collections.ObservableList) 
 * @see     java.util.Locale
 * @see     java.util.Locale#ENGLISH
 */
public void setDefaultLocale(final Locale locale);
```

```java
/**
 * Returns the {@code actual} {@link java.util.Locale}.
 * 
 * @return  the {@code actual} {@code Locale}.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 */
public Locale getActualLocale();
```

```java
/**
 * Sets the {@code actual} {@link java.util.Locale}.
 * <p>
 * If the {@code supported} Locales doesn't contained the {@code Locale} then 
 * {@code default} {@code Locale} will be used instead.
 * 
 * @param   locale the new {@code actual} {@code Locale}.
 * @throws  NullPointerException     if {@code locale} is NULL.
 * @throws  IllegalArgumentException if {@code locale} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#getDefaultLocale() 
 * @see     java.util.Locale
 */
public void setActualLocale(final Locale locale);
```

```java
/**
 * Returns the {@code actual} {@link java.util.Locale} as a {@link javafx.beans.property.ObjectProperty}.
 * <p>
 * The {@code ObjectProperty} allowed during the update from the {@code actual} 
 * {@code Locale} that all binded texts will be automatically updated.
 * 
 * @return  
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 * @see     javafx.beans.property.ObjectProperty
 */
public ObjectProperty<Locale> actualLocaleProperty();
```

```java
/**
 * Returns all {@code supported} {@code java.util.Locale}s as a {@code observable} list.
 * 
 * @return  all {@code supported} {@code Locales} as a {@code observable} list.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 */
public ObservableList<Locale> getSupportedLocales();
```

```java
/**
 * Sets the {@code supported} {@code java.util.Locale}s.
 * 
 * @param   locales 
 * @throws  NullPointerException     if {@code locales} is NULL.
 * @throws  IllegalArgumentException if {@code locales} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 */
public void setSupportedLocales(final Locale... locales);
```

```java
/**
 * Sets the {@code supported} {@code java.util.Locale}s.
 * 
 * @param   locales 
 * @throws  NullPointerException     if {@code locales} is NULL.
 * @throws  IllegalArgumentException if {@code locales} is EMPTY.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 */
public void setSupportedLocales(final ObservableList<Locale> locales);
```

### com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder<a name="I18nReBuBu" />

```java
/**
 * With the fluent builder {@code I18NResourceBundleBuilder} the developer can easily configure 
 * the {@link java.util.ResourceBundle} and the different relevant {@link java.util.Locale}s.
 * <p>
 * All steps in this fluent builder are mandory so simple follow the way :) .
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public final class I18NResourceBundleBuilder
```

```java
/**
 * Starting point from this fluent builder to configure a {@link java.util.ResourceBundle} 
 * which {@code key/value} pairs will be bind to a {@link java.util.Locale}.
 * 
 * @return  the first step to configure the {@code ResourceBundle}.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public static final FirstStep configure()
```

```java
/**
 * First mandory step to configure the {@link java.util.ResourceBundle}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.ResourceBundle
 */
public interface FirstStep {

    /**
     * Setter for the {@code path} and {@code base} name from the {@link java.util.ResourceBundle}.
     * <p>
     * The format from {@code baseBundleName} is the package name and the base 
     * name from the bundle {@code point} ('.') separated.<br>
     * For example:
     * <ul>
     * <li>com.github.naoghuman.app.i18n.demo.message</li>
     * </ul>
     * where {@code com.github.naoghuman.app.i18n.demo} is the package and 
     * {@code message} the base bundle name.
     * 
     * @param   baseBundleName which defines the path and base name from the {@code ResourceBundle}.
     * @return  the second step in this fluent builder.
     * @throws  NullPointerException     if {@code baseBundleName} is NULL.
     * @throws  IllegalArgumentException if {@code baseBundleName} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.ResourceBundle
     */
    public SecondStep baseBundleName(final String baseBundleName);

}
```

```java
/**
 * Second mandory step to configure the {@link java.util.ResourceBundle}.
 * <p>
 * In this step all {@code supported} {@link java.util.Locale}s from the 
 * {@code ResourceBundle} will be set.
 * <p>
 * Supported {@code Locale}s means that the list should contains for every 
 * supported language (language_xy.properties) the corresponding {@code Locale}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public interface SecondStep {

    /**
     * Setter for all {@code supported} {@link java.util.Locale}s from the 
     * {@link java.util.ResourceBundle}.
     * <p>
     * Supported {@code Locale}s means that the list should contains for every 
     * supported language (language_xy.properties) the corresponding {@code Locale}.
     * 
     * @param   locales
     * @return  the third step in this fluent builder.
     * @throws  NullPointerException     if {@code locales} is NULL.
     * @throws  IllegalArgumentException if {@code locales} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public ThirdStep supportedLocales(final Locale... locales);

    /**
     * Setter for all {@code supported} {@link java.util.Locale}s from the 
     * {@link java.util.ResourceBundle}.
     * <p>
     * Supported {@code Locale}s means that the list should contains for every 
     * supported language (language_xy.properties) the corresponding {@code Locale}.
     * 
     * @param   locales
     * @return  the third step in this fluent builder.
     * @throws  NullPointerException     if {@code locales} is NULL.
     * @throws  IllegalArgumentException if {@code locales} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public ThirdStep supportedLocales(final ObservableList<Locale> locales);

}
```

```java
/**
 * Third mandory step to configure the {@link java.util.ResourceBundle}.
 * <p>
 * In this step the {@code default} {@link java.util.Locale} from the 
 * {@code ResourceBundle} will be set.
 * <p>
 * Default {@code Locale} means that this Locale should be used if the 
 * {@code actual} Locale aren't in the list of supported Locales.<br>
 * If the default Locale isn't in the list of supported Locales then 
 * {@link java.util.Locale#ENGLISH} will be used instead.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setActualLocale(java.util.Locale) 
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public interface ThirdStep {

    /**
     * Setter for the {@code default} {@link java.util.Locale} from the 
     * {@link java.util.ResourceBundle}.
     * <p>
     * Default {@code Locale} means that this Locale should be used if the 
     * {@code actual} Locale aren't in the list of supported Locales.<br>
     * If the default Locale isn't in the list of supported Locales then 
     * {@link java.util.Locale#ENGLISH} will be used instead.
     * 
     * @param   locale
     * @return  the forth step in this fluent builder.
     * @throws  NullPointerException if {@code locale} is NULL.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setActualLocale(java.util.Locale) 
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public ForthStep defaultLocale(final Locale locale);

}
```

```java
/**
 * Forth mandory step to configure the {@link java.util.ResourceBundle}.
 * <p>
 * In this step the {@code actual} {@link java.util.Locale} from the 
 * {@code ResourceBundle} will be set.
 * <p>
 * Actual {@code Locale} means that this Locale should used for the message 
 * loading from the {@link java.util.ResourceBundle}.<br>
 * If the actual Locale isn't in the list of supported Locales then the 
 * {@code default} Locale will be used instead.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setDefaultLocale(java.util.Locale) 
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public interface ForthStep {

    /**
     * Setter for the {@code actual} {@link java.util.Locale} from the 
     * {@link java.util.ResourceBundle}.
     * <p>
     * Actual {@code Locale} means that this Locale should used for the message 
     * loading from the {@link java.util.ResourceBundle}.<br>
     * If the actual Locale isn't in the list of supported Locales then the 
     * {@code default} Locale will be used instead.
     * 
     * @param   locale
     * @return  the last step in this fluent builder.
     * @throws  NullPointerException if {@code locale} is NULL.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setDefaultLocale(java.util.Locale) 
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public LastStep actualLocale(final Locale locale);

}
```

```java
/**
 * The last step in this fluent builder.
 * <p>
 * With the option {@code build()} the developer completes the previsous 
 * configuration steps from the {@link java.util.ResourceBundle} and the 
 * different {@link java.util.Locale}s.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public interface LastStep {

    /**
     * Completes the previous configuration steps from the {@link java.util.ResourceBundle} 
     * and the different {@link java.util.Locale}s in this fluent builder.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public void build();

}
```

### com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder<a name="I18nReBuMeBu" />

```java
/**
 * With the fluent builder {@code I18NResourceBundleMessageBuilder} the developer can 
 * easily receive the message from the previous defined {@link java.util.ResourceBundle} 
 * in {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} in association 
 * to the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     java.util.ResourceBundle
 */
public final class I18NResourceBundleMessageBuilder
```

```java
/**
 * Starting point from this fluent builder to received a message (associated {@code value}) 
 * from the previous configure {@link java.util.ResourceBundle} in 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} in dependency from 
 * the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
 * 
 * @return  the first step to received a message.
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     java.util.ResourceBundle
 */
public static final FirstStep message()
```

```java
/**
 * First mandory step to received a message (associated {@code value}) 
 * from the previous configure {@link java.util.ResourceBundle} in 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} in dependency from 
 * the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     java.util.ResourceBundle
 */
public interface FirstStep {

    /**
     * Setter for the {@code key} which {@code value} will be loaded from the previous configure 
     * {@link java.util.ResourceBundle} in {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} 
     * in dependency from the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
     * 
     * @param   key which {@code value} should be loaded.
     * @return  the second step in this fluent builder.
     * @throws  NullPointerException     if {@code key} is NULL.
     * @throws  IllegalArgumentException if {@code key} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     * @see     java.util.ResourceBundle
     */
    public SecondStep key(final String key);

}
```

```java
/**
 * Second optional step in this fluent builder.
 * <p>
 * The developer have 2 possibilities in this step:
 * <ul>
 * <li>First complete the message process with the method {@code build()} which 
 *     will then return the {@code value} and</li>
 * <li>second add additional {@code arguments} which will then injected into the 
 *     {@code value} from the key.</li>
 * </ul>
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 */
public interface SecondStep {

    /**
     * Completes the previous configuration steps and returned the corresponding {@code value}.
     * <p>
     * Hint:<br>
     * The {@code value} will be loaded in dependency from the 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}
     * from the previous configure {@link java.util.ResourceBundle} in 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
     * 
     * @return  the loaded value.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     */
    public String build();

    /**
     * Setter for additional {@code arguments} which will injected into the 
     * {@code value} from the previous definet {@code key}.
     * 
     * @param   arguments which should be injected into the {@code value}.
     * @return  The last step in this fluent builder.
     * @throws  NullPointerException     if {@code arguments} is NULL.
     * @throws  IllegalArgumentException if {@code arguments} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     */
    public LastStep arguments(final Object... arguments);
  
}
```

```java
/**
 * The last step in this fluent builder which completes the previous configuration 
 * steps and returned the corresponding {@code value}.
 * <p>
 * Hint:<br>
 * The {@code value} will be loaded in dependency from the 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}
 * from the previous configure {@link java.util.ResourceBundle} in 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 */
public interface LastStep {
    
    /**
     * Completes the previous configuration steps and returned the corresponding {@code value}.
     * <p>
     * Hint:<br>
     * The {@code value} will be loaded in dependency from the 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}
     * from the previous configure {@link java.util.ResourceBundle} in 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
     * 
     * @return  the loaded value.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     */
    public String build();
    
}
```



Download<a name="Download" />
---

Current `version` is `0.5.0`. Main points in this release are:
* Main point in this update is writing the JavaDoc for the package 'core'.
* Update the 'api' section in the ReadMe.

**Maven coordinates**  
In context from a [Maven] project you can use following maven coordinates: 
```xml
<dependencies>
    <dependency>
        <groupId>com.github.naoghuman</groupId>
        <artifactId>lib-i18n</artifactId>
        <version>0.5.0</version>
    </dependency>
</dependencies>
```

Download:
* [Release v0.5.0] (11.18.2018 / MM.dd.yyyy)

An overview about all existings releases can be found here:
* [Overview] from all releases in `Lib-I18N`.



Requirements<a name="Requirements" />
---

* On your system you need [JRE 8] or [JDK 8] installed.
* The library [lib-i18n-0.5.0.jar](#Installation).

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
[App-I18N-Demo]:https://github.com/Naoghuman/app-i18n-demo
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
[Release v0.5.0]:https://github.com/Naoghuman/lib-i18n/releases/tag/v0.5.0
[ResourceBundle]:https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html
[Scene Builder]:https://gluonhq.com/products/scene-builder/
[StringBinding]:https://docs.oracle.com/javase/8/javafx/api/javafx/beans/binding/StringBinding.html
[UML]:https://en.wikipedia.org/wiki/Unified_Modeling_Language
