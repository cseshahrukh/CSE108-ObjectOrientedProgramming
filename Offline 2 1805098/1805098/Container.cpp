# include <iostream>
using namespace std;

# define INTEGER 1
# define INT_ARRAY 2
# define INT_MATRIX 3

// Container class can contain an integer, or an integer array or an integer matrix, but exactly one of them
class Container
{
    // Do not add any additional member variable
    int *value;
    int *valueArray;
    int **valueMatrix;
    int firstDim, secondDim;    // the dimensions of array/matrix, in case of single integer, both should be 0
    int storedType;

    // the following is a private method, not needed from outside
    void reset()
    {
        if (value != NULL)
        {
            delete value;
            // free memory occupied by value
        }
        if (valueArray != NULL)
        {
            delete []valueArray;
            // free memory occupied by valueArray
        }
        if (valueMatrix != NULL)
        {
            // free memory occupied by valueMatrix
            for (int i=0; i<firstDim; i++)
                delete []valueMatrix[i];
            delete valueMatrix;
        }
        firstDim = 0;
        secondDim = 0;
        storedType = -1;
    }

public:
    // do not write any additional public method except for those which you are asked to, according to the comments
    Container()
    {
        cout << "Constructing Container with empty parameter" << endl;
        cout << "___________________________________________" << endl;
        value = NULL;
        valueArray = NULL;
        valueMatrix = NULL;
        firstDim = 0;
        secondDim = 0;
        storedType = -1;
    }

    Container (int val)
    {
        cout << "Constructing Container with a single integer parameter" << endl;
        cout << "______________________________________________________" << endl;
        // dynamically allocate memory to the appropriate pointer and initialize it with the argument(s) of this constructor

        //setItem(val);

        value=new int ;
        *value=val;





        firstDim = 0;
        secondDim = 0;
        storedType = INTEGER;
        valueArray=NULL;
        valueMatrix=NULL;
    }

    Container (int *valArr, int len)
    {
        cout << "Constructing Container with integer array parameter" << endl;
        cout << "___________________________________________________" << endl;
        // dynamically allocate memory to the appropriate pointer and initialize with the argument(s) of this constructor
        //my code starts

        //valueArray=new int[len];

        //my code ends

        value=NULL;
        valueMatrix=NULL;
        firstDim = len;
        secondDim = 0;
        storedType = INT_ARRAY;
        valueArray=new int[len];
        for (int i=0; i<len; i++)
            valueArray[i]=valArr[i];

    }

    Container (int **valMat, int r, int c)
    {
        cout << "Constructing Container with integer matrix parameter" << endl;
        cout << "____________________________________________________" << endl;
        // dynamically allocate memory to the appropriate pointer and initialize with the argument(s)
        // assign appropriate values to the remaining member variables by observing the previous constructors
        //my code starts


        firstDim=r;
        secondDim=c;
        storedType=INT_MATRIX;
        value=NULL;
        valueArray=NULL;
        valueMatrix=new int*[r];
        for (int i=0; i<r; i++)
            valueMatrix[i]=new int [c];
        for (int i=0; i<r; i++)
            for (int j=0; j<c; j++)
                valueMatrix[i][j]=valMat[i][j];

        //my code ends
    }

    // write a copy constructor whose first two lines should be as follows:
    // cout << "Calling copy constructor of Container" << endl;
    // cout << "_____________________________________" << endl;
    // my code starts
    Container(const Container &t)
    {
        cout << "Calling copy constructor of Container" << endl;
        cout << "_____________________________________" << endl;

        if (t.value!=NULL)
        {
            setItem(*t.value);
        }
        else if (t.valueArray!=NULL)
        {
            setItem(t.valueArray, t.firstDim);

        }
        else if (t.valueMatrix!=NULL)
        {
            setItem(t.valueMatrix, t.firstDim, t.secondDim);
        }
        else
            setItem();
        firstDim=t.firstDim;
        secondDim=t.secondDim;    // the dimensions of array/matrix, in case of single integer, both should be 0
        storedType=t.storedType;


    }
    //my code ends
    void setItem()
    {

        value =NULL;
        valueArray=NULL;
        valueMatrix=NULL;
        reset();
        storedType=-1;
        firstDim = 0;
        secondDim = 0;
    }
    void setItem (int val)
    {

        value=NULL;
        valueArray=NULL;
        valueMatrix=NULL;
              reset();
        // write necessary code similar to that of the 2nd constructor
        //my code starts
        value=new int;
        *value=val;
        storedType=1;
        firstDim = 0;
        secondDim = 0;
        //my code ends
    }
    //my code starts
    void setItem(int *valArr, int len )
    {
        value=NULL;
        valueArray=NULL;
        valueMatrix=NULL;
        reset();
        valueArray=new int[len];
        for (int i=0; i<len; i++)
            valueArray[i]=valArr[i];
        storedType=2;
        firstDim = len;
        secondDim = 0;
    }
    void setItem(int **valMat, int r, int c)
    {
        value=NULL;
        valueArray=NULL;
        valueMatrix=NULL;
        reset();
        valueMatrix=new int*[r];
        for (int i=0; i<r; i++)
            valueMatrix[i]=new int [c];
        for (int i=0; i<r; i++)
            for (int j=0; j<c; j++)
                valueMatrix[i][j]=valMat[i][j];
        storedType=3;
        firstDim = r;
        secondDim = c;
    }
    //my code ends

    // overload setItem function so that it can dynamically allocate and initialize the member integer array
    // observe the 3rd constructor to understand its parameters and tasks

    // overload setItem function so that it can dynamically allocate and initialize the member integer matrix
    // observe the 4th constructor to understand its parameters and tasks

    // the following function returns a void* which can be cast to any pointer based on the storedType variable
    void * getItem()
    {
        if (value != NULL)
            return value;
        if (valueArray != NULL)
            return valueArray;
        if (valueMatrix != NULL)
            return valueMatrix;
        return NULL;
    }

    int getFirstDim()
    {
        return firstDim;
    }

    int getSecondDim()
    {
        return secondDim;
    }

    int getStoredType()
    {
        return storedType;
    }

    void print()
    {
        if (value != NULL)
        {
            cout << "There is only an integer value in the container object" << endl;
            cout << "The value is: " << *value << endl;
        }
        else if (valueArray != NULL)
        {
            cout << "There is an integer array in the container object" << endl;
            cout << "The values stored in the array are:" << endl;
            for (int i=0; i<firstDim; i++)
            {
                cout << valueArray[i] << " ";
            }
            cout << endl;
        }
        else if (valueMatrix != NULL)
        {
            cout << "There is an integer matrix in the container object" << endl;
            cout << "The values stored in the matrix are:" << endl;
            for (int i=0; i<firstDim; i++)
            {
                for (int j=0; j<secondDim; j++)
                {
                    cout << valueMatrix[i][j] << " ";
                }
                cout << endl;
            }
        }
        else
        {
            cout << "The object has no elements" << endl;
        }
    }

    ~Container()
    {

        if (value != NULL)
        {
            cout << "Freeing allocated memory for a single integer" << endl;
            // free memory occupied by value
            //my code starts
            delete value;
            //my code ends
        }
        else if (valueArray != NULL)
        {
            cout << "Freeing allocated memory for integer array" << endl;
            // free memory occupied by valueArray

            //my code starts
            delete []valueArray;
            //my code ends
        }
        else if (valueMatrix != NULL)
        {
            cout << "Freeing allocated memory for integer matrix" << endl;
            // free memory occupied by valueMatrix
            //my code starts
            for (int i=0; i<firstDim; i++)
                delete [](valueMatrix[i]);
            delete []valueMatrix;
            //my code ends
        }
        firstDim = 0;
        secondDim = 0;
        storedType = -1;
        cout << "_____________________" << endl;
        cout << "Destructing Container" << endl;
    }
};
