The Gauss-Newton Method
=======================

Directory Structure
-------------------
/release: Executable jar files
/src: Source files

Usage
-----

```
java -jar gn_[qua | exp | log | rat] -f points_file -b a,b,c -i iterations [-h | -g]

Gauss-Newton method for approximating functions.
    
    gn_qua: Approximates a quadratic function in the form f(x) = ax^2 + bx + c
    gn_exp: Approximates a exponential function in the form f(x) = ae^(bx)+ c
    gn_log: Approximates a logarithmic function in the form f(x) = aln(x+b) + c
    gn_rat: Approximates a rational function in the form f(x) = ax/(x+b) + c
    -f points_file: CSV of x,y coordiate pairs
    -b a,b,c: a, b, and c componenets of the initial Beta vector
    -i iterations: Number of iterations
    -h Use Householder reflections
    -g Use Given's rotations
```