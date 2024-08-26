### BUKKIT VAVR PLAYGROUND :: FUNCTIONAL PROGRAMMING IN JVM PLATFORM
* this is a personal-use repository, where i want to outline the need of the vavr.io framework in bukkit/spigot software development.
* [vavr.official](https://www.vavr.io/)
* [vavr.git](https://github.com/vavr-io/vavr)
* [vavr.docs](https://www.javadoc.io/doc/io.vavr/vavr)


# Main fp concepts to know during the introduction:
* [architecture](https://github.com/noyzys/bukkit-vavr-in-action/tree/main/src/main/java/dev/noyzys/bukkit/vavr) conveys habits of functional code based on bukkit software.
* I recommend the book version FP in Java

# Functional programming (FP) 
* was introduced  into Java in Java 8. 
* Additional Functional Programming Enhancements were introduced in Java 9 until today is developed
**Functional Programming is an essential skill for Java Programmers today.**

# Immutability
* An immutable class is simply a class whose instances cannot be modified. 
* All of the information contained in each instance is provided when it is created and is fixed for the lifetime of the object.

# Persistant Data Structures (PDS)
* A persistent data structure does preserve the previous version of itself when being modified and is therefore effectively immutable.

# Referential Transparency (RT)
* An expression is called referentially transparent if it can be replaced with its corresponding value without changing the program's behavior. 
* This requires that the expression is pure, that is to say the expression value must be the same for the same inputs and its evaluation must have no side effects. 
* An expression that is not referentially transparent is called referentially opaque.

# Collections
* io.vavr -> collections are functional data structures. 
* We need to recover a few concepts to understand functional data structures in vavr.

# Other Abstractions ex.
* monoid, 
* monad,
* functor,
* semigroup, 
* natural, 
* random number generator, 
* reader,
* writer, 
* state, 
* input/output (IO),
* parser,
* zipper,
* specification based testing (quickcheck),
* actors, 
* optics (lens, prism, fold, traversal and others), 
* concurrency and type conversion.

## SO WE COMBINE..
* Immutability
* Persistant Data Structures
* Referential Transparency
* AND ⬇⬇⬇⬇ 

# 1. FUNCTOR:
* A Functor is a datatype that preserves structure and implements the map function.
* Preserving structure simply means that you can put a value inside and the structure itself won’t change it.
* Map (or mapper/fmap/flatmap) is a function that takes a function as an argument and applies that function to the values in the structure it operates on.

# 2. MONAD:
* A monad is a structure that combines program fragments (functions), and wraps their return values in a type with additional computation.
* Example from java java.util.Optional - Monad

# 3. MONOIDS:
* A Monoid is a design pattern expressing function composition of items in a set,
 is a function that takes two arguments and returns an argument of that type and it supports both the identity property.

# 4. FUNCTION:
* Core building blocks of a functional program are variables and functions rather than objects and methods, 
* pure functions.

# IN KOTLIN:
* A higher-order function is a function that takes one or more functions as arguments or returns a function as a result.
* In Kotlin, We can define higher-order functions using lambda expressions. `( (A, B) -> C )`.
* Example from kotlin functor `fold`.
* A pure function doesn't depend on anything other than its input parameters.

# SCALA:
* [scala.lg](https://github.com/scala)

**I skip many impl collections from the io.vavr.collection package because the implementation is similar is immutable**
# Impl Event subscribe, CommandExecutor:
* [BukkitCommandExecutor](https://github.com/noyzys/bukkit-vavr-in-action/blob/main/src/main/java/dev/noyzys/bukkit/vavr/IBukkitCommandExecutor.java)
```java
final class CustomCommand implements IBukkitCommandExecutor {
    
  @Override
  public void execute(final Player player, final String[] args) {
   // impl
  }

  @Override
  public String getName() {
   return "commandName";
  }
 }
```

* [Register Commands](https://github.com/noyzys/bukkit-vavr-in-action/blob/main/src/main/java/dev/noyzys/bukkit/vavr/BukkitVavrPlugin.java#L57)
```java
    final List<IBukkitCommandExecutor> commands = List.of(
            new CustomCommand(),
            new OtherCustomCommand(this),
            new HujsonCommand()
        );

        commands.forEach(command -> Option.of(getCommand(command.getName()))
                        .peek(cmd -> cmd.setExecutor(command)));
```

* [BukkitListener](https://github.com/noyzys/bukkit-vavr-in-action/blob/main/src/main/java/dev/noyzys/bukkit/vavr/BukkitVavrListener.java)
```java
final class BukkitVavrListener implements Listener {

    @EventInvoker(PlayerJoinEvent.class)
    void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(">> " + player.getName());
    }
}
```

* [Register Listeners with handling](https://github.com/noyzys/bukkit-vavr-in-action/blob/main/src/main/java/dev/noyzys/bukkit/vavr/BukkitVavrPlugin.java#L61)
```java
    final EventListenerRegistrar listenerRegistrar = new EventListenerRegistrar(this);
        Try.run(() -> listenerRegistrar.subscribeToEvents(this, new CustomListener()))
            .onFailure(ex -> getLogger().severe("> Could not register events: " + ex.getMessage()));

```

* Call Bukkit Event Handler
```java
    final EventListenerRegistrar listenerRegistrar = new EventListenerRegistrar(this);
    listenerRegistrar.callEvent(new CustomEvetHandler());
```

**Another repository based on fucked up rx (Reactive Extensions implementation for Java)**
[reactor.std](https://projectreactor.io/)
[reactor.core](https://projectreactor.io/docs/core/release/reference/)
[reactor.git](https://github.com/reactor/reactor-core)
[reactor.javadoc](https://projectreactor.io/docs/core/release/api/)
