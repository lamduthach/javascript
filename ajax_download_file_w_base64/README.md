## Overview
This project show how use ajax to download file with base64 convert

### Reason
Usually, we use Form Download HTML to create action download file and call backend<br>
#### Form HTML download
Pros :
- Easy to implement
- Dont care about Encode/Decode for another types
- Could work with Large file<br>
Cons :
- Cant monitor or detect result dowload file (success/error also write file in client too)

#### Ajax way
Pros :
- Complex
- Have to convert to another type to hanle (base64)
- Can monitor or detect result download file<br>
Cons :
- Should not work with Large file
