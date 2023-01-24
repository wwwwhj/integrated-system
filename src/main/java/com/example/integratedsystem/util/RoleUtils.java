package com.example.integratedsystem.util;

/**
 * @author Wu Hongjian
 */
public enum RoleUtils {
    ADMIN(0,"admin"),/* 管理员 */
    INSTRUCTOR(1,"instructor"),/* 指导教师 */
    STUDENT(2,"student");/* 学生 */

    private static RoleUtils[] roles = new RoleUtils[]{ADMIN, INSTRUCTOR, STUDENT};

    private int roleId;
    private String roleType;

    private RoleUtils(int roleId, String roleType){
        this.roleId=roleId;
        this.roleType = roleType;
    }

    public static String getRoleType(int roleId){
        for (RoleUtils role : roles){
            if (role.roleId==roleId){
                return role.roleType;
            }
        }
        return "";
    }

    public static boolean isAdmin(int roleId){
        return ADMIN.roleId==roleId;
    }

    public static boolean isAdmin(String roleType){
        return ADMIN.roleType.equals(roleType);
    }

    public static boolean isInstructor(int roleId){
        return INSTRUCTOR.roleId==roleId;
    }

    public static boolean isInstructor(String roleType){
        return INSTRUCTOR.roleType.equals(roleType);
    }

    public static boolean isStudent(int roleId){
        return STUDENT.roleId==roleId;
    }

    public static boolean isStudent(String roleType){
        return STUDENT.roleType.equals(roleType);
    }

}
