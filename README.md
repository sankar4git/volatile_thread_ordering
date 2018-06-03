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

 if (thID == (counter%total)) {
					System.out.println("thread " + thID + " prints " + c);
					if(c=='Z'){
						c='A';
					}else{
						c=(char)((int)c+1);
					}
					System.out.println("thread " + thID + " prints " + print++);
					counter++;					
	}


1> only one thread can enter the outermost if condition and till counter++ no other thread can enter.

2> You know in multithreaded scenario actions of one thread may not be visible to another thread. There is a statement counter++ and counter is volatile. So all the prior assignments of variable c and variable print in this thread will be visible to other threads, as one of these threads satisfies if condition and enters the block, after counter is modified by current thread.

That's all. Cheers!
