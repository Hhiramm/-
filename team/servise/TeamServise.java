package exer130.team.servise;

import exer130.team.domain.Architect;
import exer130.team.domain.Designer;
import exer130.team.domain.Employee;
import exer130.team.domain.Programmer;

public class TeamServise {
    private static  int counter = 1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;
    /**
     * 返回当前团队的所有对象
     * @return 包含所有成员对象的数组，数组大小与成员人数一致
     */
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * 向团队中添加成员
     * @param e 待添加成员的对象
     * @throws TeamException 添加失败， TeamException中包含了失败原因
     */
    public void addMember(Employee e) throws TeamException{
//        成员已满，无法添加
        if(total >= MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
//        该成员不是开发人员，无法添加
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
//        该员工已是某团队成员
//        该员工正在休假，无法添加
        Programmer p = (Programmer) e;
        Status status = p.getStatus();
        switch (status){
            case BUSY:
                throw new TeamException("员工已是某团队成员");
            case VOCATION:
                throw new TeamException("该员工正在休假，无法添加");
        }
//        该员工已在本开发团队中
        boolean isExist = isExist(p);
        if (isExist){
            throw new TeamException("该员工已在本开发团队中");
        }
//        团队中至多只能有一名架构师
//        团队中至多只能有两名设计师
//        团队中至多只能有三名程序员
        //记录程序员、设计师、架构师的个数
        int progNum, desNum, arcNum = 0;
        progNum = desNum = arcNum = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect){
                arcNum++;
            } else if (team[i] instanceof Designer) {
                desNum++;
            }else {
                progNum++;
            }
        }
        if(p instanceof Architect){
            if(arcNum >= 1){
                throw new TeamException("团队中至多只能有一个架构师");
            }
        }
        if(p instanceof Designer){
            if(desNum >= 2){
                throw new TeamException("团队中至多只能有两名设计师");
            }
        }
        if(p instanceof Programmer){
            if(desNum >= 3){
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }
        //如果代码能执行到这，意味着p是可以添加到team数组中的
        team[total++] = p;
        p.setMemberId(counter++);
        p.setStatus(Status.BUSY);
    }


    /**
     * 判断p是否存在于当前的开发团队中
     * @param p
     * @return
     */
    private boolean isExist(Programmer p) {
        for (int i = 0; i < total; i++) {
            if(team[i].getId() == p.getId()){
                return true;
            }
        }
        return false;
    }

    /**
     * 从团队中删除成员
     * @param menberId 从团队中删除成员
     * @throws TeamException 找不到指定memberId的员工，删除失败
     */
    public void removeMember(int menberId) throws TeamException{
        int i = 0;
        for (; i < total; i++) {
            if(team[i].getMemberId() == menberId){
                //找到了
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if(i == total){
            throw new TeamException("找不到指定memberId的员工，删除失败\n");
        }
        //调整数组
        for(int j = i; j < total; j++){
            team[j] = team[j + 1];
        }
        team[--total] = null;
    }
}
