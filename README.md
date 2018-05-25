# volatile_thread_ordering
This code shows how powerful the volatile keyword is.
volatile cannot replace "synchronized" keyword totally. But can definitely be a better alternative in some of the situations.

Say, we need to order the activities of a few threads. This is a common problem. Say each of the threads need to perform some steps atomically. After a thread 0 completes, thread 1 will complete those steps atomically and so on. So it needs to be guarantied that thread 0, 1, 2 and so on will do their job atomically in this ordered manner. Following is the desired output. Following is an example desired output.

thread 0 prints A
thread 0 prints 1
thread 1 prints B
thread 1 prints 2
thread 2 prints C
thread 2 prints 3
thread 3 prints D
thread 3 prints 4


The code does this gracefully. How volatile makes this possible. See the following snippet.

if (thID == counter) {					
  System.out.println("thread " + thID + " prints " + c);
  if(c=='Z'){						
    c='A';					
  }else{						
    c=(char)((int)c+1);					
  }					
  System.out.println("thread " + thID + " prints " + print++);
  counter++;					
  if (counter == total)						
    counter = 0;				
}


1> only one thread can enter the outermost if condition and till counter++ no other thread can enter.

2> You know in multithreaded scenario actions of one thread may appear to be in different order to another thread. As volatile keeps the order of statements intact, the two print output statements appear in the same order, as they look in the code. Because there is a statement count++ and count is volatile. So all the prior statements will be ordered.

That's all. Cheers!
