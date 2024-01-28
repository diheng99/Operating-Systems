int
main(int argc, char **argv)
{
    int argCount;                       // the number of arguments
                                        // for a particular command

    DEBUG('t', "Entering main");
    (void) Initialize(argc, argv);

    ThreadTest(); //Start ThreadTest();

    for (argc--, argv++; argc > 0; argc -= argCount, argv += argCount) {
        argCount = 1;
        if (!strcmp(*argv, "-z"))               // print copyright
            printf (copyright);
#ifdef USER_PROGRAM
        if (!strcmp(*argv, "-x")) {             // run a user program
            ASSERT(argc > 1);
            StartProcess(*(argv + 1));
            argCount = 2;
        } else if (!strcmp(*argv, "-c")) {	// test the console
            if (argc == 1)
                ConsoleTest(NULL, NULL);
            else {
                ASSERT(argc > 2);
                ConsoleTest(*(argv + 1), *(argv + 2));
                argCount = 3;
            }
            interrupt->Halt();          // once we start the console, then
                                        // Nachos will loop forever waiting
                                        // for console input
        }
#endif // USER_PROGRAM
#ifdef FILESYS
        if (!strcmp(*argv, "-cp")) {            // copy from UNIX to Nachos
            ASSERT(argc > 2);
            Copy(*(argv + 1), *(argv + 2));
            argCount = 3;
        } else if (!strcmp(*argv, "-p")) {	// print a Nachos file
            ASSERT(argc > 1);
            Print(*(argv + 1));
            argCount = 2;
        } else if (!strcmp(*argv, "-r")) {	// remove Nachos file
            ASSERT(argc > 1);
            fileSystem->Remove(*(argv + 1));
            argCount = 2;
        } else if (!strcmp(*argv, "-l")) {	// list Nachos directory
            fileSystem->List();
        } else if (!strcmp(*argv, "-D")) {	// print entire filesystem
            fileSystem->Print();
        } else if (!strcmp(*argv, "-t")) {	// performance test
            PerformanceTest();
        }
#endif // FILESYS
#ifdef NETWORK
	if (!strcmp(*argv, "-o")) {
            ASSERT(argc > 1);
            Delay(2);                           // delay for 2 seconds
                                                // to give the user time to
                                                // start up another nachos
            MailTest(atoi(*(argv + 1)));
            argCount = 2;
        }
#endif // NETWORK
    }

    currentThread->Finish();    // NOTE: if the procedure "main"
                                // returns, then the program "nachos"
                                // will exit (as any other normal program
                                // would).  But there may be other
                                // threads on the ready list.  We switch
                                // to those threads by saying that the
                                // "main" thread is finished, preventing
                                // it from returning.
    return(0);                  // Not reached...
}

