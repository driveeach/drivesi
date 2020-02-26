#ifndef OFMACROS_H
#define OFMACROS_H
// Macros ----------------------------------------------------------------------
#ifdef OF_THREADS
	#define OF_LOCK(m) if(mutex) pthread_mutex_lock(&this->m)
	#define OF_UNLOCK(m) if(mutex) pthread_mutex_unlock(&this->m)
	#define OF_DMUTEXV , bool mutex
	#define OF_DMUTEXVD , bool mutex = true
	#define OF_DMUTEX bool mutex
	#define OF_DMUTEXD bool mutex = true
	#define OF_MUTEX mutex
	#define OF_MUTEXV , mutex
	#define OF_MUTEXT this->mutex
	#define OF_MUTEXVT , this->mutex
	#define OF_MUTEXFALSE false
	#define OF_MUTEXFALSEV , false
	#define OF_MUTEXDO(m) m
	#define OF_TREADER_QTDSQL 1000 //select dividido por blocos
	
#else
	#define OF_LOCK(m)
	#define OF_UNLOCK(m)
	#define OF_DMUTEXV
	#define OF_DMUTEXVD
	#define OF_DMUTEX
	#define OF_DMUTEXD
	#define OF_MUTEX
	#define OF_MUTEXV
	#define OF_MUTEXT
	#define OF_MUTEXVT
	#define OF_MUTEXFALSE
	#define OF_MUTEXFALSEV
	#define OF_MUTEXDO
	
#endif
#ifdef OF_DEBUG
	#define OF_ASSERT(c) assert(c)
	#ifdef OF_THREADS
		#define OF_ASSERTLOCK(c,m) pthread_mutex_lock(&this->m);OF_ASSERT(c);pthread_mutex_unlock(&this->m)
	#else
		#define OF_ASSERTLOCK(c,m) OF_ASSERT(c)
	#endif
#else
	#define OF_ASSERT(c)
	#define OF_ASSERTLOCK(c,m)
#endif
// Definicoes ------------------------------------------------------------------
#define OF_FLAGS_TYPE ids
#define OF_FLAGS_INMESH (sizeof(OF_FLAGS_TYPE)*8-1)
#define OF_ERRO 1.0e-15
#define OF_ERRO_INCIRCLE 1.0e-20
#define MAX_BUFFER 255
#endif
