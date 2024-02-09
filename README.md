# Vectors-Operations-On-Different-Thread-Model
We are testing to see how using multiple threads will affect the speed it take compute
Z = c ∗ V + Y
where C is a constant and Z, V, and Y are vectors of integers of 1GB size. The first test is just using a
single thread to get the base speed. The second test is having threads work on adjacent indexes and then
computing their next space after n places, where n is the number of threads. The final test is when we have
each thread work on a contiguous block of the thread. We test theses up to 2c threads and observe how they
speed up or slow down, where c is the number of cores on the lambda machine.

Single threaded (one thread does all the multiplications and additions)
Multi-threaded, where "adjacent" threads scale and add adjacent elements of X and Y. That, is Thread t  will compute the indices t, t + n, t + 2n, ... were n is the number of threads.
Multi-threaded, where threads break the vector into contiguous block of roughly the same size, and each thread works on those blocks.

This is parellel Progamming Homework 1
[PPHW1Report Sam Nowak.pdf](https://github.com/SamNowak/Vectors-Operations-On-Different-Thread-Model/files/14223723/PPHW1Report.Sam.Nowak.pdf)
