select Max(Salary)as SecondHighestSalary
from Employee
where salary<(Select MAX(Salary) from Employee);
