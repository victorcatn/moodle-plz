package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class AlgorithmServiceTest {

    private AlgorithmService algorithmService = new AlgorithmService();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateAlgorithm() {
        List<StaffMember> staffMembers = new ArrayList<>();

        /*--------------------------------------------------Test 1---------------------------------------------------*/

        Project project1 = new Project(
                "P00000000000000000000001",
                "test",
                null,
                null,
                new ArrayList<>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10),
                        new SkillScore("5b96f1b0291ec123846beef4", 10),
                        new SkillScore("5ba07559291ec12a00df060c", 10)
                )),
                new ArrayList<>(Arrays.asList(
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
                true,
                new ArrayList<>(Collections.singletonList(
                        new SkillScore("5ba07565291ec12a00df060d", 10)
                )),
                new ArrayList<>(Arrays.asList(
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
                true,
                new ArrayList<>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 55),
                        new SkillScore("5b96f1b0291ec123846beef4", 55),
                        new SkillScore("5ba07559291ec12a00df060c", 55),
                        new SkillScore("5ba07559291ec12a00df06012", 100),
                        new SkillScore("5ba07559291ec12a00df06023", 55)
                )),
                new ArrayList<>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",55),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",55),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 55)
                ))
        );
        staffMembers.add(staffMember2);


        Algorithm algorithm = algorithmService.generateAlgorithm(staffMembers, project1);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().contains(staffMember2));
        Assert.assertTrue(algorithm.getOthers().contains(staffMember1));
        Assert.assertTrue(algorithm.getNonSuggestedStaffMember().isEmpty());



        /*--------------------------------------------------Test 2---------------------------------------------------*/


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
                true,
                new ArrayList<>(Collections.singletonList(
                        new SkillScore("5ba07565291ec12a00df060d", 50)
                )),
                new ArrayList<>(Collections.singletonList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec", 50)
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
                true,
                new ArrayList<>(Collections.singletonList(
                        new SkillScore("5ba07565291ec12a00df060a", 50)
                )),
                new ArrayList<>(Collections.singletonList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ea", 50)
                ))
        );
        staffMembers.add(staffMember4);

        algorithm = algorithmService.generateAlgorithm(staffMembers, project1);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().contains(staffMember2));
        Assert.assertFalse(algorithm.getSuggestedStaffMember().contains(staffMember3)
                || algorithm.getSuggestedStaffMember().contains(staffMember4)
                || algorithm.getSuggestedStaffMember().contains(staffMember1));
        Assert.assertTrue(algorithm.getOthers().contains(staffMember1)
                && algorithm.getOthers().contains(staffMember3));
        Assert.assertFalse(algorithm.getNonSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getNonSuggestedStaffMember().contains(staffMember4));

        /*--------------------------------------------------Test 3---------------------------------------------------*/

        Project project2 = new Project();

        algorithm = algorithmService.generateAlgorithm(staffMembers, project2);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getOthers().isEmpty());
        Assert.assertEquals(4, algorithm.getNonSuggestedStaffMember().size());

        /*--------------------------------------------------Test 4---------------------------------------------------*/

        Project project3 = new Project();
        List<StaffMember> staffMembers2 = new ArrayList<>();

        algorithm = algorithmService.generateAlgorithm(staffMembers2, project3);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getNonSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getOthers().isEmpty());

        /*--------------------------------------------------Test 5---------------------------------------------------*/

        Project project4 = new Project(
                "P00000000000000000000004",
                "test",
                null,
                null,
                new ArrayList<>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10),
                        new SkillScore("5b96f1b0291ec123846beef4", 10),
                        new SkillScore("5ba07559291ec12a00df060c", 10)
                )),
                new ArrayList<>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",10),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",10),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 10)
                )),
                null);

        List<StaffMember> staffMembers3 = new ArrayList<>();

        algorithm = algorithmService.generateAlgorithm(staffMembers3, project4);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getNonSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getOthers().isEmpty());

        /*--------------------------------------------------Test 5---------------------------------------------------*/
        Project project5 = new Project(
                "P00000000000000000000004",
                "test",
                null,
                null,
                new ArrayList<>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10),
                        new SkillScore("5b96f1b0291ec123846beef4", 10),
                        new SkillScore("5ba07559291ec12a00df060c", 10)
                )),
                new ArrayList<>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",10),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",10),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 10)
                )),
                null);

        List<StaffMember> staffMembers4 = new ArrayList<>();

        StaffMember staffMember5 = new StaffMember(
                "000000000000000000000005",
                "1152464229",
                "riosangel8@gmail.com",
                "5233801.",
                "Paola Andrea",
                "Castañeda Osorno",
                false,
                true,
                new ArrayList<>(Collections.singletonList(
                        new SkillScore("5ba07565291ec12a00df060a", 50)
                )),
                new ArrayList<>(Collections.singletonList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ea", 50)
                ))
        );
        staffMembers4.add(staffMember5);

        algorithm = algorithmService.generateAlgorithm(staffMembers4, project5);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getNonSuggestedStaffMember().contains(staffMember5));
        Assert.assertTrue(algorithm.getOthers().isEmpty());

        /*--------------------------------------------------Test 6---------------------------------------------------*/
        Project project6 = new Project(
                "P00000000000000000000004",
                "test",
                null,
                null,
                new ArrayList<>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10),
                        new SkillScore("5b96f1b0291ec123846beef4", 10),
                        new SkillScore("5ba07559291ec12a00df060c", 10)
                )),
                new ArrayList<>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",10),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",10),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 10)
                )),
                null);

        List<StaffMember> staffMembers5 = new ArrayList<>();

        StaffMember staffMember6 = new StaffMember(
                "000000000000000000000005",
                "1152464229",
                "riosangel8@gmail.com",
                "5233801.",
                "Paola Andrea",
                "Castañeda Osorno",
                false,
                true,
                new ArrayList<>(Arrays.asList(
                        new SkillScore("5ba07565291ec12a00df060d", 10),
                        new SkillScore("5b96f1b0291ec123846beef4", 10),
                        new SkillScore("5ba07559291ec12a00df060c", 10)
                )),
                new ArrayList<>(Arrays.asList(
                        new KnowledgeScore("5b96e6b4291ec11abcebf6ec",10),
                        new KnowledgeScore("5b96e6cb291ec11abcebf6ed",10),
                        new KnowledgeScore("5b96e69f291ec11abcebf6eb", 10)
                ))
        );
        staffMembers5.add(staffMember6);

        algorithm = algorithmService.generateAlgorithm(staffMembers5, project6);

        Assert.assertTrue(algorithm.getSuggestedStaffMember().contains(staffMember6));
        Assert.assertTrue(algorithm.getNonSuggestedStaffMember().isEmpty());
        Assert.assertTrue(algorithm.getOthers().isEmpty());

    }
}