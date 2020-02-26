#ifndef HANDLER_HPP_
#define HANDLER_HPP_

template<typename _T>
struct Handler{
   
   typedef _T     TType;
   
private:   
   TType    *value_;
   
   
public:
   Handler(){};
   
   Handler(TType *v): value_(v){}

public:
   TType* 
   operator->() const {
      return this->value_;
   }
   
   TType* 
   operator&() const {
      return this->value_;
   }
   
   TType& 
   operator*() const {
      return *this->value_;
   }
   
   void
   Set(TType *v){
      this->value_ = v;
   }
   
};

#endif /*HANDLER_HPP_*/
