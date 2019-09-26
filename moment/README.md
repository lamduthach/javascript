# Introduce Moment.js
Web => https://momentjs.com/ <br>
Docs => https://momentjs.com/docs/ <br>
Github => https://github.com/moment/moment

## Javascript pure way data handle

- Syntax:<br>
```javascript
// notthing worry
new Date(); 
// value is long type
new Date(value); 
// quite not easy to code, maintain
new Date(dateString); 
// not easy to code, maintain, easy make mistake
new Date(year, month(Index of months), date, hours, minutes, seconds, milliseconds); 
```
- Example:<br>
```javascript
// Thu Sep 26 2019 15:08:28 GMT+0900 (Japan Standard Time)
new Date() 
// Wed Oct 02 2019 00:00:00 GMT+0900 (Japan Standard Time) - omg, that mean i have to know and define right month name
new Date("October 02, 2019") 
// Wed Oct 30 2019 09:00:00 GMT+0900 (Japan Standard Time)
new Date("2019-10-30") 
// Invalid Date - omg that mean javascript could not auto match dateformat, it dateformat is static years -> month -> date (YYYY-MM-DD)
new Date("2019-30-10") 
// Expect : Wed Oct 02 2019 00:00:00 GMT+0900 (Japan Standard Time)
// Result : Sat Nov 02 2019 00:00:00 GMT+0900 (Japan Standard Time)
// Omg, be careful when define this way, month here not `month`, its index of months
new Date(2019, 10, 2)
// Thu Apr 01 2021 00:00:00 GMT+0900 (Japan Standard Time)
// Omg, very very funny, its could work too
new Date(2019, 25, 60)
```
- Compare and Calculate
```javascript
// Date some api
var date = new Date();
date.getDate(); // 26
date.getDay() // 4
date.getFullYear(); // 2019
date.getHours(); // 15
date.getMilliseconds() // 871
date.getMinutes(); // 29
date.getMonth(); // 8
date.getYear(); // 119
date.getSeconds(); // 48
date.getTime(); // 1569479388871

// compare
var date1 = new Date("October 02, 2019");
var date2 = new Date("2019-10-30");
var diff1 = date2 - date1; // 2451600000 (time in milliseconds - long value)
var diff2 = date2.getTime() - date1.getTime(); // 2451600000 (time in milliseconds - long value)
```
Omg that almost we could do with Date
Lets jumb to Moment.js

## MomentJS way
- Syntax:<br>
```javascript
// default
moment("String define", "String date format")
// wap Date
var date = new Date();
var m = moment(date);
```

- Example:<br>
```javascript
moment("20191002", "YYYYMMDD").toDate(); // Wed Oct 02 2019 00:00:00 GMT+0900 (Japan Standard Time)
moment("10-02-2019", "MM-DD-YYYY").toDate(); // Wed Oct 02 2019 00:00:00 GMT+0900 (Japan Standard Time)
moment("10-02-2019", "DD-MM-YYYY").toDate(); // Sun Feb 10 2019 00:00:00 GMT+0900 (Japan Standard Time)
```
- Date Validation : <br>
```javascript
var a = moment("2019-19-22T00:00:00");
a.isValid(); // false
a.invalidAt(); // 1 - check is date valid or not
// The return value has the following meaning:
// 0 : years | 1 : months | 2 : days | 3 : hours | 4 : minutes | 5 : seconds | 6 : milliseconds
```

- Difference
```javascript
var a = moment();
a.format("YYYYMMDD"); // "20190926"
var b = moment("20191002", "YYYYMMDD");
b.diff(a); // 458881935
b.diff(a, 'days'); // 5
b.diff(a, 'years'); // 0
b.diff(a, 'years', true); // 0.015877413953400126
b.diff(a, 'months'); // 0
b.diff(a, 'months', true); // 0.1905289674408015
```
- Parse
```javascript
moment().toDate(); // Thu Sep 26 2019 16:36:19 GMT+0900 (Japan Standard Time)
moment().toString(); // "Thu Sep 26 2019 16:37:19 GMT+0900"
moment().toArray(); // [2019, 8, 26, 16, 36, 44, 976]
moment().toObject(); // { years:2019, months:8, date:26, hours:16, minutes:40, seconds:52, milliseconds:758 }
```
