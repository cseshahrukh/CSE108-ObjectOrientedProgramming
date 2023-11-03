public class Department {

    private int id;
    private String name;
    private Employee [] employees;
    private int employeeCount;
    // add your code here
    public static int departmentCount;
    public static Department departments[]=new Department[20];
    // there can be at most 20 departments  

    // you are not allowed to write any other constructor
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new Employee[10];
        // add your code here
        departments[departmentCount++]=this;
    }

    // add your code here
    void addEmployee(Employee emp){
        employees[employeeCount++]=emp;
    }
    public double getDepartmentSalary(){
        double total=0;
        //for (Employee e:employees){ //This will get null point exception because initially size is 10
        //So it will iterate from 0 to 9 th index
          //  total+=e.getSalary();
        //}
        for (int i=0; i<employeeCount; i++){
            total+=employees[i].getSalary();
        }
        return total;
    }
    Employee getMaxSalaryEmployee(){
        double maxSalary=0;
        Employee maxEmployee=null;
        for (int i=0; i<employeeCount; i++){
            if (employees[i].getSalary()>maxSalary){
                maxSalary=employees[i].getSalary();
                maxEmployee=employees[i];
            }
        }
        return maxEmployee;
    }
    public static double getTotalSalary(){
        double totalSalary=0;
        for (int i=0; i<departmentCount; i++ )
        {
            //totalSalary+=departments[i].getDepartmentSalary();
            Department d=departments[i];
            for (int j=0; j<d.employeeCount; j++)

                totalSalary+=d.employees[j].getSalary();
        }
        return totalSalary;
    }
}