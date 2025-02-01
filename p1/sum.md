# חומר לתוכנה 1:
## הרצאות
### הרצאה 1
- המרת פרימיטיביים
- switch case
- while + do while
- for, foreach
- continue, break
### הרצאה 2
שם מלא (qualified name), מופעים, עצמים, שרותים, פונק איתחול (בנאי), סטטי, מקומי/גלובלי, Heap, final, בנאי ברירת מחדל, records, calling by value, Stack/Heap, tException in thread "main" java.lang, שרותי ומשתני מופע ומחלקה,
*העשרה: *static blocks

### הרצאה 3
- העמסה
- ההעדפה של הקומפיילר: לעשות casting כמה שיותר חלש.
- אי־בהירות
- העמסת מספר לא ידוע של ארגומנטים
- טיפוסי מניה
- switch-case
- ספק/לקוח
- חוזים:
  * תנאי קדם (precondition)
  * תנאי בתר (postcondition)
- משתמר (invariant)
*העשרה:* packages, שם מלא (fully qualified name) של מחלקה, imports, CLASSPATH, jar, javadoc, & API

*הרצאה 4*
- מנשקים: default & private methods in an interface, ייתרון בעבור לקוחות, מנשק כחוזה
- שאילתות צופות ומפיקות
- האצלה, פעפוע, הכמסה, הפשטה (וטפ תשתמשו באנגלית)
- toString
- immutable objects, קבעון שטחי ועמוק
- פולימורפיזם
- מפעלים (factories)

### מדריך IO
IO

###הרצאות 5-6
- שיבוטים: clonable, shallow copy, deep copy, Object.clone(), מנשקי סימון
- מנשקים עם חתימה זהה
- הכלה לעומת האצלה (aggeration vs. delegation)
- יחס הרכבה (composition) לעומת האצלה
- יחס generalization -- is-a
- ירושה, בנאים במחלקות יורשות (חובת קריאה)
- overriding
- LSP (עקרון ההחלפה)
- static vs. dynamic typing
- static vs. dynamic type (unrelated)
- נראות (protected, public, private, package-private)
- final class, final methods
- Object
- Collection, List, Queue (FIFO), SortedMap, Map, Set, SortedSet
- מחלקה מופשטת (abstract class)
- מנשקים לעומת הורשה (ייתרונות בכתיבת API ועוד)
- casting (לא פרימיטיבי)ת downcast, upcast
- instanceof
- dynamic dispatch (virtual) vs. static bindings
- devirtualization
- ambiguous method call
- assertions

### הרצאות 7-8
- גנרים
- wrappers, boxing & unboxing
- inner classes
- Iterable (עם מתודה iterator()), Iterator
- הסקת טיפוסים (var) ומגבלותיה (בעבור גנריים, טיפוסים לא מאותחלים, בחתימת פונ' ועוד)

### הרצאה 9
- הצהרה, זריקה וטיפול בחריגים.
- cheked vs. unchecked exceptions, the Throwable, Error, RunTimeException and Exeption classes
- throwing vs. preconditions
- ריבוי בלוקי catch, בלוק finally
- try-with-resources
- חריג כעצם, מחסנית הקריאות, עלות שימוש במנגנון החריגים
- implementations of collections
- sorting an collection, Comprable, Comperator

### הרצאה 10
- מנשק אנונימי, מנשק פונקציונלי, lambda expressions + קיצורים
- streams, שירותים סופניים
- intermediate: filter, limit, skip, map, sorted, peek
- terminal: all/any/noneMatch, collect, count, forEach, reduce
- Optional, Collectors

### הרצאה 11
- Bridge design pattern
- הנדסת תוכנה, הבדל בין Programs ל־software products
- מודלים לפיתוח תוכנה: waterfall, agile, מימושים של agile: TDD, kanban
- design

### הרצאה 12
- Testing, CRs, Formal proofs
- Uni, Integraion, System, Acceptance and Regressions testing
- Black box vs. White box
- Testing life cycle + testing tools

### הרצאה 13 -- חזרה על חומרים
- covariance vs. invariance
- type erasure
- הורשת חריגים, ניראות והעמסה
- wilcards
- חסמים לג'וקרים
- observer design pattern

## חומרי תרגולים
### תרגול 1
- גיט וגיטהאב
- מנהלות
- כלים (Java 21, JRE, JVM, STD, JDK, JRE, IDE)
### תרגול 2
- מחרוזות, מערכים, המחלקה Arrays
- סוליד בקצרה: שכפול קוד, SRP
- Complication vs. Runtime
- breakpoints
### תרגול 3
- getters/setters, constructors, constructors overloading (העמסת בנאים), נראות, main
- instance vs class fields, מתי מאותחלים, איך ניגשים.
- stack trace
### תרגול 4
- הרחבות על ה־JVM וה־JRE
- by value / by reference
- singileton design pattern (בקורס: מימוש lazy)

### תרגול 5
רק מעבר על פולימורפיזם ו־interfaces

### תרגול 6
חזרה על חומר של ההרצאה: הכלה, האצלה, דריסה, נראות, הורשה, מחלקות מופשטות, צד ספק.

### תרגול 7
- הכמסה מוגברת (wtf)
- staic and non-static nested classes
- חזרה על static ו־dynamic bingings

### תרגול 8
- טיפוסים נאים
- diamond operator
- מתודות גנריות
חזרה על חומרים מההרצאה: ג'וקרים וחסימתם

### תרגול 9
חזרה על ההרצאה: חריגים, collections, comparator & comparable

### תרגול 10
חזרה על ההרצאה: זרמים ואיטרטורים

### תרגול 11
עיצוב תוכנה

### תרגול 12
חומר: כל ההרצאות, כל התרגולים, כל תרגלי הבית. חומר סגור. חלק פתוח עם שאלת design וחלק אמריקאי.
חזרה על חומר.
