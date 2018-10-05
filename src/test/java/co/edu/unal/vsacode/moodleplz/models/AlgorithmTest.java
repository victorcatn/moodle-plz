package co.edu.unal.vsacode.moodleplz.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmTest {

    @Test
    public void searchPerfectMatch() {
    }

    @Test
    public void searchSuggestedMembers() {
    }

    @Test
    public void searchOthersMembers() {
    }

    @Test
    public void searchNonSuggestedMembers() {

        List<StaffMember> staffMembers = new ArrayList<>();

        /**--------------------------------------------------Test 1---------------------------------------------------*/

        Project project1 = new Project(
                "P00000000000000000000001",
                "test",
                null,
                null,
                new ArrayList<SkillScore>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10),
                        new SkillScore("5b96f1b0291ec123846beef4", 10),
                        new SkillScore("5ba07559291ec12a00df060c", 10)
                )),
                new ArrayList<KnowledgeScore>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",10),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",10),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 10)
                )),
                null);

        /*staffmember1 must be othes*/
        StaffMember staffMember1 = new StaffMember(
                "000000000000000000000001",
                "1152464229",
                "riosangel8@gmail.com",
                "5233801.",
                "Paola Andrea",
                "Castañeda Osorno",
                false,
                new ArrayList<SkillScore>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10)
                )),
                new ArrayList<KnowledgeScore>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",10),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",10)
                ))
        );
        staffMembers.add(staffMember1);

        /*StaffMember2 cumple con los criterios de los nodos en buena medida
         *seberia ser sugeriodo
         */
        StaffMember staffMember2 = new StaffMember(
                "000000000000000000000002",
                "1152464229",
                "riosangel8@gmail.com",
                "5233801.",
                "Paola Andrea",
                "Castañeda Osorno",
                false,
                new ArrayList<SkillScore>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 55),
                        new SkillScore("5b96f1b0291ec123846beef4", 55),
                        new SkillScore("5ba07559291ec12a00df060c", 55),
                        new SkillScore("5ba07559291ec12a00df06012", 100),
                        new SkillScore("5ba07559291ec12a00df06023", 55)
                )),
                new ArrayList<KnowledgeScore>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",55),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",55),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 55)
                ))
        );
        staffMembers.add(staffMember2);


        Algorithm algorithm = new Algorithm(staffMembers, project1);
        //TODO assertEquals
        System.out.println(algorithm.getMemberWeightMap().toString());


        /**--------------------------------------------------Test 2---------------------------------------------------*/


        /*StaffMember3 cumple la condicion del nodo 1 y del nodo 2 pero en muy poca medida
        * deberia estar dentro de otros*/
        StaffMember staffMember3 = new StaffMember(
                "000000000000000000000003",
                "1152464229",
                "riosangel8@gmail.com",
                "5233801.",
                "Paola Andrea",
                "Castañeda Osorno",
                false,
                new ArrayList<SkillScore>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 50)
                )),
                new ArrayList<KnowledgeScore>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",50)
                ))
        );
        staffMembers.add(staffMember3);

        /*the staffMember4 must have don´t suggested
        * no cumple las condiciones del nodo 1, 2 ni 3*/
        StaffMember staffMember4 = new StaffMember(
                "000000000000000000000004",
                "1152464229",
                "riosangel8@gmail.com",
                "5233801.",
                "Paola Andrea",
                "Castañeda Osorno",
                false,
                new ArrayList<SkillScore>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060a", 50)
                )),
                new ArrayList<KnowledgeScore>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ea",50)
                ))
        );
        staffMembers.add(staffMember4);

        algorithm = new Algorithm(staffMembers, project1);
        //TODO assertEquals
        System.out.println(algorithm.getMemberWeightMap().toString());


    }
}