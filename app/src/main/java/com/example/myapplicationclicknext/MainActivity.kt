package com.example.myapplicationclicknext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var textResult1: TextView
    private lateinit var textResult2: TextView
    private lateinit var textResult3: TextView
    private lateinit var textSalary: TextView

    private var mEmployeeList = ArrayList<Employee>()

    data class Employee(var name:String,var salary:Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)
        setInitView()
        addEmployee(Employee(name = "A",salary = 5000))
        addEmployee(Employee(name = "B",salary = 9000))
        addEmployee(Employee(name = "C",salary = 7500))
        sumtaxInfo()
    }

    private fun setInitView() {
        textResult1 = findViewById(R.id.textResult1)
        textResult2 = findViewById(R.id.textResult2)
        textResult3 = findViewById(R.id.textResult3)
        textSalary = findViewById(R.id.textSalary)
    }

    private fun addEmployee(employee: Employee) {
        mEmployeeList.add(employee)
    }

    private fun showEmployee() {
        textResult1.text = "Name: ${mEmployeeList[0].name} , Salary: ${mEmployeeList[0].salary}"
        textResult2.text = "Name: ${mEmployeeList[1].name} , Salary: ${mEmployeeList[1].salary}"
        textResult3.text = "Name: ${mEmployeeList[2].name} , Salary: ${mEmployeeList[2].salary}"
    }

    private fun salaryInfo() {
        var sum_salary:Double = 0.0
        for(value in mEmployeeList){
            sum_salary += value.salary
        }
        textSalary.text = "${sum_salary}"
    }

    private fun taxInfo() {
        textResult1.text = "Name: ${mEmployeeList[0].name} , " +
                            "Salary: ${mEmployeeList[0].salary}, " +
                            "Tax SSO: ${calculateTaxSSO(mEmployeeList[0].salary)} , " +
                            "Bonus: ${calculateBonus(mEmployeeList[0].salary)} , "  +
                            "Income (NET): ${(mEmployeeList[0].salary-calculateTaxSSO(mEmployeeList[0].salary))+calculateBonus(mEmployeeList[0].salary)}"
        textResult2.text = "Name: ${mEmployeeList[1].name} , " +
                            "Salary: ${mEmployeeList[1].salary}, " +
                            "Tax SSO: ${calculateTaxSSO(mEmployeeList[1].salary)} , " +
                            "Bonus: ${calculateBonus(mEmployeeList[1].salary)} , "  +
                            "Income (NET): ${(mEmployeeList[1].salary-calculateTaxSSO(mEmployeeList[1].salary))+calculateBonus(mEmployeeList[1].salary)}"
        textResult3.text = "Name: ${mEmployeeList[2].name} , " +
                            "Salary: ${mEmployeeList[2].salary}, " +
                            "Tax SSO: ${calculateTaxSSO(mEmployeeList[2].salary)} , " +
                            "Bonus: ${calculateBonus(mEmployeeList[2].salary)} , "  +
                            "Income (NET): ${(mEmployeeList[2].salary-calculateTaxSSO(mEmployeeList[2].salary))+calculateBonus(mEmployeeList[2].salary)}"
    }

    private fun fundInfo(){
        var emp_tax = IntArray(3)
        var emp_fund = IntArray(3)
        var com_fund = IntArray(3)
        var emp_salary = IntArray(3)
        for( i in 0..2){
            emp_tax[i] = calculateTaxSSO(mEmployeeList[i].salary)
            emp_salary[i] = (mEmployeeList[i].salary - emp_tax[i]) - calculateTaxSSO(mEmployeeList[i].salary - emp_tax[i])
            emp_fund[i] = calculateTaxSSO(mEmployeeList[i].salary-emp_tax[i])
            com_fund[i] = calculateCompanyFund(mEmployeeList[i].salary - emp_tax[i])
        }
        textResult1.text = "Name: ${mEmployeeList[0].name} , " +
                "Salary (NET): ${emp_salary[0]} ," +
                "Employee Fund: ${emp_fund[0]} , " +
                "Company Fund: ${com_fund[0]}"
        textResult2.text = "Name: ${mEmployeeList[1].name} , " +
                "Salary (NET): ${emp_salary[1]} ," +
                "Employee Fund: ${emp_fund[1]} , " +
                "Company Fund: ${com_fund[1]}"
        textResult3.text = "Name: ${mEmployeeList[2].name} , " +
                "Salary (NET): ${emp_salary[2]} ," +
                "Employee Fund: ${emp_fund[2]} , " +
                "Company Fund: ${com_fund[2]}"
    }

    private fun sumtaxInfo(){
        var emp_tax = IntArray(3)
        var emp_fund = IntArray(3)
        var com_fund = IntArray(3)
        var emp_salary = IntArray(3)
        var emp_sum_salary = IntArray(3)
        for( i in 0..2){
            emp_tax[i] = calculateTaxSSO(mEmployeeList[i].salary)
            emp_salary[i] = (mEmployeeList[i].salary - emp_tax[i]) - calculateTaxSSO(mEmployeeList[i].salary - emp_tax[i])
            emp_fund[i] = calculateTaxSSO(mEmployeeList[i].salary-emp_tax[i])
            com_fund[i] = calculateCompanyFund(mEmployeeList[i].salary - emp_tax[i])

            emp_sum_salary += mEmployeeList[i].salary - emp_tax[i]

        }
        textResult1.text = "Name: ${mEmployeeList[0].name} , " +
                "Salary (NET): ${emp_salary[0]} ," +
                "Employee Fund: ${emp_fund[0]} , " +
                "Company Fund: ${com_fund[0]}"
        textResult2.text = "Name: ${mEmployeeList[1].name} , " +
                "Salary (NET): ${emp_salary[1]} ," +
                "Employee Fund: ${emp_fund[1]} , " +
                "Company Fund: ${com_fund[1]}"
        textResult3.text = "Name: ${mEmployeeList[2].name} , " +
                "Salary (NET): ${emp_salary[2]} ," +
                "Employee Fund: ${emp_fund[2]} , " +
                "Company Fund: ${com_fund[2]}"
    }

    private fun calculateTaxSSO(salary:Int): Int{
        return if(salary < 15000){
            (salary * 5)/100
        }else{
            (15000 * 5)/100
        }
    }
    private fun calculateBonus(salary:Int): Int{
        return (salary * 0.5).roundToInt()
    }

    private fun calculateCompanyFund(salary:Int): Int{
        return (salary * 2)/100
    }





}
