#ifndef	OFKDTREE_H
#define	OFKDTREE_H
#include "ofMacros.h"
#include "ofList.h"
namespace of
{
template<class sObject> struct ofKdTreeNode
{
    sObject	ptr;
	bool deleted;
    ofKdTreeNode *p[2];
	ofKdTreeNode();
	~ofKdTreeNode();
};
template <class sObject> ofKdTreeNode<sObject>::ofKdTreeNode()
{
	deleted=false;
	p[0]=NULL;
	p[1]=NULL;
}
template <class sObject> ofKdTreeNode<sObject>::~ofKdTreeNode()
{
}
template <class sObject, class sObjectCompare> class ofKdTree 
{
public:	
    ofKdTree(int dim);
    ~ofKdTree();
    void insert(sObject obj);   
	//bool erase(sObject obj);
	
	sObject nearest(sObject obj);	
	sObject nearestAndInsert(sObject obj);
	sObject findNN(sObject obj);
	
    
    int size();
    
private:
    
	ofKdTreeNode<sObject> *add(sObject obj);
	ofKdTreeNode<sObject> *nearestSearch(sObject obj);
    void searchNN(ofKdTreeNode<sObject> *node,sObject target,ofKdTreeNode<sObject> &p,double &dr,int s);
    sObjectCompare comp;
	
	ofKdTreeNode<sObject> *root;
    int n, ndim;
};
template <class sObject, class sObjectCompare> ofKdTree<sObject,sObjectCompare>::ofKdTree(int dim) 
{
	OF_ASSERT(dim);
	
	root = NULL;
	ndim = dim;
	n = 0;
}
template <class sObject, class sObjectCompare> ofKdTree<sObject,sObjectCompare>::~ofKdTree()
{
	ofList< ofKdTreeNode<sObject> * > lista;
		
	ofKdTreeNode<sObject> *node = root;
		
	while(node)
	{
		if(node->p[0])
			lista.insert(node->p[0]);
		if(node->p[1])
			lista.insert(node->p[1]);
			
		delete node;
		
		lista.erase(0);
				
		if(!lista.empty())
			node = lista.first();
		else
			node = NULL;
	}
}
template <class sObject, class sObjectCompare> void ofKdTree<sObject,sObjectCompare>::insert(sObject obj)
{
	add(obj);	
}
template <class sObject, class sObjectCompare> sObject ofKdTree<sObject,sObjectCompare>::findNN(sObject obj)
{
	double dr =1000000000.0;
	ofKdTreeNode<sObject> p;
	searchNN(root,obj,p,dr,0);
	if(dr!=1000000000.0)
		return p.ptr;
	else
		return NULL;
}
template <class sObject, class sObjectCompare> sObject ofKdTree<sObject,sObjectCompare>::nearest(sObject obj)
{
	ofKdTreeNode<sObject> *node = nearestSearch(obj);
	
	if(node)
		return node->ptr;
	else
		return NULL;
}
template <class sObject, class sObjectCompare> sObject ofKdTree<sObject,sObjectCompare>::nearestAndInsert(sObject obj)
{
	ofKdTreeNode<sObject> *node = nearestSearch(obj);
	
	add(obj);
	
	if(node)
		return node->ptr;
	else
		return NULL;
}
template <class sObject, class sObjectCompare> ofKdTreeNode<sObject>*ofKdTree<sObject,sObjectCompare>::add(sObject obj)
{
	ofKdTreeNode<sObject> *leaf = root, *ant = NULL;
	int ld = 0, dim = 0;
	
	while(leaf)
	{
		if(comp.greater(leaf->ptr, obj, dim))
		{
			ant = leaf;
			ld = 0;
		}
		else
		{
			ant = leaf;
			ld = 1;
		}
		leaf = ant->p[ld];
		if(++dim == ndim)
			dim = 0;
	}
	
	leaf = new ofKdTreeNode<sObject>;
    
	leaf->ptr = obj;
    leaf->p[0]  = NULL;
    leaf->p[1] = NULL;
	
	if(ant)
		ant->p[ld] = leaf;
	else
	{
		root = leaf;
		ant = root;
	}
		
	n++;
	
	return ant;
}
template <class sObject, class sObjectCompare> void ofKdTree<sObject,sObjectCompare>::searchNN(ofKdTreeNode<sObject> *node,sObject target,ofKdTreeNode<sObject> &p,double &dr,int s)
{
	if(node)
	{
		double d;
		if((node->p[0]==NULL)&&(node->p[1]==NULL))
		{
			d=comp.distance(node->ptr,target);
			if(d<dr)
			{
				dr=d;
				p=*node;
			}
		}
		else
		{
			if(dr==1000000000.0)
			{
				if(comp.greater(node->ptr,target,s))
				{
					
					searchNN(node->p[0],target,p,dr,(s+1)%3);
					if(comp.distComp(target,node->ptr,dr,s)) 
						searchNN(node->p[1],target,p,dr,(s+1)%3);
							
				}
				else
				{
					searchNN(node->p[1],target,p,dr,(s+1)%3);
					if(comp.distComp1(target,node->ptr,dr,s)) 
						searchNN(node->p[0],target,p,dr,(s+1)%3);	
				}
			}
			else
			{
				
						if(comp.distComp1(target,node->ptr,dr,s)) 
						{
							d=comp.distance(node->ptr,target);
							if(d<dr)
							{
								dr=d;
								p=*node;
							}
							searchNN(node->p[0],target,p,dr,(s+1)%3);
						}
					
						if(comp.distComp(target,node->ptr,dr,s)) 
						{
							d=comp.distance(node->ptr,target);
							if(d<dr)
							{
								dr=d;
								p=*node;
							}
							searchNN(node->p[1],target,p,dr,(s+1)%3);
						}
					
			}
			
		}
	}
	
}
template <class sObject, class sObjectCompare> ofKdTreeNode<sObject>* ofKdTree<sObject,sObjectCompare>::nearestSearch(sObject obj)
{
	ofKdTreeNode<sObject> *leaf = root, *ant = NULL;
	int ld, dim = 0;
	
	while(leaf)
	{
		if(comp.greater(leaf->ptr, obj, dim))
		{
			ant = leaf;
			ld = 0;
		}
		else
		{
			ant = leaf;
			ld = 1;
		}
		leaf = ant->p[ld];
	
		if(++dim == ndim)
			dim = 0;
	}
	
	if(ant)
		return ant;
	else
		return NULL;
}
template <class sObject, class sObjectCompare> int ofKdTree<sObject,sObjectCompare>::size()
{
	return n;
}
}
#endif
