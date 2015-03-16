Parameter Values:
  * year
  * weekOfYear
  * month (1)
  * dayOfMonth
  * hour
  * minute
  * dayOfWeek (2)
  * second

Usage:
```
Password password = new ExtendedPasswordBuilder().createPassword("pass", TimeParameter.YEAR);
```



---


1.
  * Calander.JANUARY = 0
  * Calander.FEBRUARY = 1
  * Calander.MARCH = 2
  * Calander.APRIL = 3
  * Calander.MAY = 4
  * Calander.JUNE = 5
  * Calander.JULY = 6
  * Calander.AUGUST = 7
  * Calander.SEPTEMBER = 8
  * Calander.OCTOBER = 9
  * Calander.NOVEMBER = 10
  * Calander.DECEMBER = 11


2.
  * Calendar.SUNDAY = 1
  * Calendar.MONDAY = 2
  * Calendar.TUESDAY = 3
  * Calendar.WEDNESDAY = 4
  * Calendar.THURSDAY = 5
  * Calendar.FRIDAY = 6
  * Calendar.SATURDAY = 7