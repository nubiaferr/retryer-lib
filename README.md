# Retryer

A lightweight and extensible retry library written in Kotlin, designed using the Strategy Pattern and coroutine-friendly APIs.

---

## ✨ Features

* Retry once or multiple times
* Fixed delay between retries
* Exponential backoff support
* Coroutine-based (non-blocking)
* Fully extensible and composable design

---

## 🧠 Design

This project follows Object-Oriented Design principles with a focus on:

* **Composition over inheritance**
* **Single Responsibility Principle**
* **Open/Closed Principle**

Retry behavior is defined through two independent strategies:

* **StopMethod** → determines *when to stop retrying*
* **WaitMethod** → determines *how long to wait between retries*

This makes it easy to plug in new strategies without modifying existing code.

---

## 🚀 Usage

### Basic retry (retry once)

```kotlin
val retryer = Retryer()

retryer.execute {
    println("Trying...")
    callApi()
}
```

---

### Retry multiple times

```kotlin
val retryer = Retryer(
    stopMethod = TryMaxTimes(3)
)

retryer.execute {
    callApi()
}
```

---

### Fixed delay between retries

```kotlin
val retryer = Retryer(
    stopMethod = TryMaxTimes(3),
    waitMethod = FixedWait(1000)
)

retryer.execute {
    callApi()
}
```

---

### Exponential backoff

```kotlin
val retryer = Retryer(
    stopMethod = TryMaxTimes(3),
    waitMethod = ExponentialBackoff(500)
)

retryer.execute {
    callApi()
}
```

---

## 📈 Exponential Backoff

The delay increases exponentially after each failure:

```
delay = base * 2^attempt
```

Example (base = 500ms):

* 1st retry → 500ms
* 2nd retry → 1000ms
* 3rd retry → 2000ms

---

## 🧪 Testing

The project includes unit tests using:

* JUnit
* kotlinx-coroutines-test

Tests cover:

* Single retry behavior
* Multiple retries
* Retry flow with different strategies

---

## 🔧 Dependencies

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

testImplementation("junit:junit:4.13.2")
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
```

---

## 🚧 Future Improvements

* Add jitter to avoid retry spikes
* Retry based on exception type
* Timeout-based stop strategy
* Pluggable logging/metrics
* Dispatcher injection for better testability


