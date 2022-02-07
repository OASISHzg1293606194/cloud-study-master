package com.hzg.principle.lkp;

/**
 * @Package: com.hzg.principle.lkp
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 17:19
 */
public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        System.out.println("================不符合LKP================");
        boss.commandCheckNumber(teamLeader);
        System.out.println("================符合LKP================");
        boss.commandCheckNumberCorrect(teamLeader);
    }
}
