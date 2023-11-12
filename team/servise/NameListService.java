package exer130.team.servise;

import exer130.team.domain.*;

import static exer130.team.servise.Data.*;
public class NameListService {
    private Employee[] employees;

    public NameListService(){
        employees = new Employee[EMPLOYEES.length];
        for (int i = 0; i < employees.length; i++) {
            int type = Integer.parseInt(Data.EMPLOYEES[i][0]);      //员工的类型
            //获取通用属性
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;
            switch (type){
                case EMPLOYEE:
                    employees[i] = new Employee(id,name,age,salary);
                    break;
                case PROGRAMMER:
                    equipment = creatEquipment(i);
                    employees[i] = new Programmer(id,name,age,salary,equipment);
                    break;
                case DESIGNER:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id,name,age,salary,equipment,bonus);
                    break;
                case ARCHITECT:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id,name,age,salary,equipment,bonus,stock);
                    break;
            }
        }
//        根据项目提供的Data类构建相应大小的employees数组
//        再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，以及相关联的Equipment子类的对象
//                将对象存于数组中
//        Data类位于com.atguigu.team.service包中
    }

    private Equipment creatEquipment(int index){
        int equipmentType = Integer.parseInt(EQUIPMENTS[index][0]);
        String modelOrName = EQUIPMENTS[index][1];
        String priceOrDisplayOrType = EQUIPMENTS[index][2];
        switch (equipmentType){
            case PC:
                return new PC(modelOrName,priceOrDisplayOrType);
            case NOTEBOOK:
                double price = Double.parseDouble(priceOrDisplayOrType);
                return new NoteBook(modelOrName,price);
            case PRINTER:
                return new Printer(modelOrName,priceOrDisplayOrType);
        }
        return null;
    }
    public Employee[] getAllEmployees(){
        return employees;
    }

    /*
    获取指定id对应的员工
     */
    public Employee getEmployee(int id) throws TeamException{
        for(int i = 0; i < employees.length; i++){
            if(id == employees[i].getId()){
                return employees[i];
            }
        }
        //没找到
        throw new TeamException("找不到指定的员工");
    }
}
