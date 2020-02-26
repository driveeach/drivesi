#ifndef HISTOGRAM_HPP_
#define HISTOGRAM_HPP_

#include <iostream>
#include <string>

/*
 * How to use
 * 
 * Histogram   histo(numSlots, valMin, valMax, "Title");
 * for
 *    histo.AddValue(newValue);
 * histo.Print();
 * histo.PrintGnuplot();
 * */


class Histogram{

public:	
	typedef   double      TValue;
   
protected:
   typedef   unsigned short       TSize;
   typedef   std::vector<TSize>   THistogram;
 
protected:
 
   TSize                size_;
   TValue               max_, min_;
   int                  underflow_, overflow_;
   
   TValue               subRange_;  // (max_ - min_)/size_
    
   THistogram           histogram_;    
    
public:

   string               title_;

public:

   Histogram(TSize piSize, TValue piMin, TValue piMax, string piTitle="Histogram")
      :size_(piSize), 
       min_(piMin), 
       max_(piMax), 
       title_(piTitle), 
       histogram_(size_,0),
       underflow_(0),
       overflow_(0)
   {
      
      
   	assert(this->size_);
   	assert(this->max_ > this->min_);
      
   	
   	this->subRange_ = (this->max_ - this->min_) / this->size_;

   };   
   
   
public:   
   
   void
   AddValue(TValue piV){
   	
   	TSize slot;

cout<<"max   "<<max_<<"     subRange " <<subRange_<<endl;      
   	
   	if(piV < this->min_) 
   	   ++this->underflow_;
      else if(piV <= this->max_){
            slot = piV / this->subRange_; 
            ++(this->histogram_[slot]);
      }else{
         ++this->overflow_;
      }
   	
   }
   
	
	ostream&
	Print(ostream &pios){
	
	   pios << this->title_ << std::endl;
		
		THistogram::iterator it  = this->histogram_.begin(), 
		                     end = this->histogram_.end();
		                     
		int       count=0;                     
		TValue    total=0;
		for( ;it!=end; ++it){
			pios << count << "  " << *it << std::endl;
			total += *it;
		}
		
		if(this->underflow_) 
		   pios<<"underflow:  "<< this->underflow_ << std::endl;
		
		if(this->overflow_) 
		   pios<<"overflow:  "<< this->overflow_ << std::endl;
		   
		   
		pios << endl << "total  " << total << endl;
		
	}
	
	ostream&
	PrintGnuplot(ostream &pios){
		
		THistogram::iterator it  = this->histogram_.begin(), 
		                     end = this->histogram_.end();
		                     
		int       count=0;                     
		TValue    total=0;
		for( ;it!=end; ++it){
			pios << count << "  " << *it << std::endl;
			total += *it;
		}
	}
	
};


#endif /*HISTOGRAM_HPP_*/
