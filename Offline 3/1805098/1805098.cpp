#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;
class StringMath
{
    char* p;
    //int len;
public:
// Add necessary constructors and destructors and functions
    StringMath()
    {
        p=NULL;
    }
    StringMath(const char *s)
    {
        if (s[0]=='-'){
            cout<<"Invalid"<<endl;
            exit(1);
        }
        int len=strlen(s);
        p=new char[len];
        strcpy(p,s);
    }
    StringMath (const StringMath &a);
    StringMath operator+(StringMath obj);
    StringMath operator++(int unused);
    StringMath &operator=(StringMath obj);
    bool operator>(int n);
    bool operator>(StringMath ob);
    friend StringMath operator+(StringMath ob, int n);
    friend StringMath operator+(int n, StringMath ob);
    void print();
    ~StringMath()
    {
        delete []p;
    }

};
void StringMath:: print()
{
    cout<<p<<endl;
}
StringMath::StringMath(const StringMath &ob)
{

        p=new char[strlen(ob.p)+1]; //new construction er somoy always allocate korte hobe. p null hok ba nahok
    //p te garbage thakle  null nao hote pare
    strcpy(p,ob.p);
}
StringMath StringMath::operator+(StringMath obj)
{
    char *a, *b; //a te choto ta b te boro ta
    if (strlen(p)<strlen(obj.p))
    {
        a=new char[strlen(p)];
        strcpy(a, p);
        b=new char[strlen(obj.p)];
        strcpy(b,obj.p);
    }
    else
    {
        a=new char[strlen(obj.p)];
        strcpy(a, obj.p);
        b=new char[strlen(p)];
        strcpy(b,p);
    }

    int n1=strlen(a), n2=strlen(b);
    int n=max(n1, n2)+1;
    char *summation=new char[n];
   int carry=0;
    int i, j, k;
    k=0, j=n2-1;
    for (int i=n1-1; i>=0; i--, j--, k++)
    {
        int sum= (a[i]-'0')  +  (b[j]-'0')   + carry ;
        summation[k]= sum%10 + '0';
        carry=sum/10;
    }
    for (; j>=0; j--, k++)
    {
        int sum= (b[j]-'0') + carry;
        summation[k]= sum%10 + '0';
        carry=sum/10;
    }
    if (carry)
    {
        summation[k]=carry+'0';
    }
    for (int i=0; i<strlen(summation)/2; i++) //reverse
    {
        char temp;
        temp=summation[i];
        summation[i]=summation[strlen(summation)-i-1];
        summation[strlen(summation)-i-1]=temp;
    }
    //for (int i=0; i<strlen(summation); i++)
     //   cout<<summation[i];
    //cout<<endl;
    //cout<<strlen(summation)<<endl;
    StringMath temp(summation);
    return temp;
}

StringMath &StringMath::operator=(StringMath ob)
{
    if (!p)
    {
        p=new char[strlen(ob.p)+1];
    }
    else if (strlen(p)<strlen(ob.p))
    {
        delete []p;
        p=new char[strlen(ob.p)+1];
    }
    strcpy(p,ob.p);
    //cout<<p<<endl;
    return *this;
}

bool StringMath::operator>(int n){
    if (n<0) return true;

    char *buffer;
    int siz=0;
    int t=n;
    while (t)
    {
        siz++;
        t/=10;
    }
    buffer=new char[siz];
    for (int i=0; i<siz; i++)
    {
        buffer[i]=n%10+'0';
        n=n/10;
    }
    for (int i=0; i<strlen(buffer)/2; i++) //reverse
    {
        char temp;
        temp=buffer[i];
        buffer[i]=buffer[strlen(buffer)-i-1];
        buffer[strlen(buffer)-i-1]=temp;
    }
//    for (int i=0; i<strlen(buffer); i++)
//        cout<<buffer[i];
//    cout<<endl;
    if (strlen(p)!=strlen(buffer))
        return strlen(p)>strlen(buffer);
    for (int i=0; i<strlen(p); i++)
    {
        if (p[i]!=buffer[i])
            return p[i]>buffer[i];
    }
    return false;
}
bool StringMath::operator>(StringMath ob)
{
    if (strlen(p)!=strlen(ob.p))
        return strlen(p)>strlen(ob.p);
    for (int i=0; i<strlen(p); i++)
    {
        if (p[i]!=ob.p[i])
            return p[i]>ob.p[i];
    }
    return false;
}
StringMath operator+(StringMath ob, int n)
{
char *buffer;
    int siz=0;
    int t=n;
    while (t)
    {
        siz++;
        t/=10;
    }
    buffer=new char[siz];
    for (int i=0; i<siz; i++)
    {
        buffer[i]=n%10+'0';
        n=n/10;
    }
    for (int i=0; i<strlen(buffer)/2; i++) //reverse
    {
        char temp;
        temp=buffer[i];
        buffer[i]=buffer[strlen(buffer)-i-1];
        buffer[strlen(buffer)-i-1]=temp;
    }
    StringMath temp(buffer);
    temp=temp+ob;
    return temp;
}
StringMath operator+(int n, StringMath ob)
{
    return ob+n;
}
StringMath StringMath::operator++(int unused)
{
    StringMath temp(*this);
     *this=*this+1;
     return temp;
}
int main()
{
    StringMath S1;
    StringMath S2("123");
    StringMath S3("757");
    StringMath S4("220");
    StringMath S5;
    int n=345;
    S1=S4;
//Print the character string of S1 and S4
    S1.print();
    S4.print();
    S1=S2+S3+S4;
    //S1.print();
//Print the character string of S1, S2, S3 and S4, where S1 contains the character string: “1100”
    S1.print();
    S2.print();
    S3.print();
    S4.print();

    S5=S4=S3;
    //
// Print the character string of S5, S4 and S3
S5.print();
S4.print();
S3.print();
    if(S3 > n )
    {
       S5= S3+n;
       //cout<<"ok boss"<<endl;

   }
// Print the character string of S5, where S5 contains the character string: “1102”
S5.print();
    S5= n+S2;

// Print the character string of S5, where S5 contains the character string: “468”
 S5.print();
    if(S5 > S2)
    {
        S5++; //Assume prefix increment
    }
    S5.print();
// Print the character string of S5, where S5 contains the character string: “469”
}
