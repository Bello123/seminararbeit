package com.daniel.semarbeit.user;

import java.util.stream.Stream;

/**
 *
 * @author Daniel
 */
public enum Notes {
    
    R           (-1, "R"),
    C_0         (0, "C 0"),
    C_SHARP_DB_0(1, "C Sharp Db 0"),
    D_0         (2, "D 0"),
    D_SHARP_EB_0(3, "D Sharp Eb 0"),
    E_0         (4, "E_0"),
    F_0         (5, "F_0"),
    F_SHARP_GB_0(6, "F Sharp Gb 0"),
    G_0         (7, "G 0"),
    G_SHARP_AB_0(8, "G Sharp Ab 0"),
    A_0         (9, "A 0"),
    A_SHARP_BB_0(10, "A Sharp Bb 0"),
    B_0         (11, "B 0"),
    C_1         (12, "C 1"),
    C_SHARP_DB_1(13, "C Sharp Db 1"),
    D_1         (14, "D 1"),
    D_SHARP_EB_1(15, "D SHARP Eb 1"),
    E_1         (16, "E 1"),
    F_1         (17, "F 1"),
    F_SHARP_GB_1(18, "F Sharp Gb 1"),
    G_1         (19, "G 1"),
    G_SHARP_AB_1(20, "G Sharp Ab 1"),
    A_1         (21, "A 1"),
    A_SHARP_BB_1(22, "A Sharp Bb 1"),
    B_1         (23, "B 1"),
    C_2         (24, "C 2"),
    C_SHARP_DB_2(25, "C Sharp Db 2"),
    D_2         (26, "D 2"),
    D_SHARP_EB_2(27, "D Sharp Eb 2"),
    E_2         (28, "E 2"),
    F_2         (29, "F 2"),
    F_SHARP_GB_2(30, "F Sharp Gb 2"),
    G_2         (31, "G 2"),
    G_SHARP_AB_2(32, "G Sharp Ab 2"),
    A_2         (33, "A 2"),
    A_SHARP_BB_2(34, "A Sharp Bb 2"),
    B_2         (35, "B 2"),
    C_3         (36, "C 3"),
    C_SHARP_DB_3(37, "C Sharp Db 3"),
    D_3         (38, "D 3"),
    D_SHARP_EB_3(39, "D Sharp Eb 3"),
    E_3         (40, "E 3"),
    F_3         (41, "F 3"),
    F_SHARP_GB_3(42, "F Sharp Gb 3"),
    G_3         (43, "G 3"),
    G_SHARP_AB_3(44, "G Sharp Ab 3"),
    A_3         (45, "A 3"),
    A_SHARP_BB_3(46, "A Sharp Bb 3"),
    B_3         (47, "B 3"),
    C_4         (48, "C 4"),
    C_SHARP_DB_4(49, "C Sharp Db 4"),
    D_4         (50, "D 4"),
    D_SHARP_EB_4(51, "D Sharp Eb 4"),
    E_4         (52, "E 4"),
    F_4         (53, "F 4"),
    F_SHARP_GB_4(54, "F Sharp Gb 4"),
    G_4         (55, "G 4"),
    G_SHARP_AB_4(56, "G Sharp Ab 4"),
    A_4         (57, "A 4"),
    A_SHARP_BB_4(58, "A Sharp Bb 4"),
    B_4         (59, "B 4"),
    C_5         (60, "C 5"),
    C_SHARP_DB_5(61, "C Sharp Db 5"),
    D_5         (62, "D 5"),
    D_SHARP_EB_5(63, "D Sharp Eb 5"),
    E_5         (64, "E 5"),
    F_5         (65, "F 5"),
    F_SHARP_GB_5(66, "F Sharp Gb 5"),
    G_5         (67, "G 5"),
    G_SHARP_AB_5(68, "G Sharp Ab 5"),
    A_5         (69, "A 5"),
    A_SHARP_BB_5(70, "A Sharp Bb 5"),
    B_5         (71, "B 5"),
    C_6         (72, "C 6"),
    C_SHARP_DB_6(73, "C Sharp Db 6"),
    D_6         (74, "D 6"),
    D_SHARP_EB_6(75, "D Sharp Eb 6"),
    E_6         (76, "E 6"),
    F_6         (77, "F 6"),
    F_SHARP_GB_6(78, "F Sharp Gb 6"),
    G_6         (79, "G 6"),
    G_SHARP_AB_6(80, "G Sharp Ab 6"),
    A_6         (81, "A 6"),
    A_SHARP_BB_6(82, "A Sharp Bb 6"),
    B_6         (83, "B 6"),
    C_7         (84, "C 7"),
    C_SHARP_DB_7(85, "C Sharp Db 7"),
    D_7         (86, "D 7"),
    D_SHARP_EB_7(87, "D Sharp Eb 7"),
    E_7         (88, "E 7"),
    F_7         (89, "F 7"),
    F_SHARP_GB_7(90, "F Sharp Gb 7"),
    G_7         (91, "G 7"),
    G_SHARP_AB_7(92, "G Sharp Ab 7"),
    A_7         (93, "A 7"),
    A_SHARP_BB_7(94, "A Sharp Bb 7"),
    B_7         (95, "B 7"),
    C_8         (96, "C 8"),
    C_SHARP_DB_8(97, "C Sharp Db 8"),
    D_8         (98, "D 8"),
    D_SHARP_EB_8(99, "D Sharp Eb 8"),
    E_8         (100, "E 8"),
    F_8         (101, "F 8"),
    F_SHARP_GB_8(102, "F Sharp Gb 8"),
    G_8         (103, "G 8"),
    G_SHARP_AB_8(104, "G Sharp Ab 8"),
    A_8         (105, "A 8"),
    A_SHARP_BB_8(106, "A Sharp Bb 8"),
    B_8         (107, "B 8"),
    C_9         (108, "C 9"),
    C_SHARP_DB_9(109, "C Sharp Db 9"),
    D_9         (110, "D 9"),
    D_SHARP_EB_9(111, "D Sharp Eb 9"),
    E_9         (112, "E 9"),
    F_9         (113, "F 9"),
    F_SHARP_GB_9(114, "F Sharp Gb 9"),
    G_9         (115, "G 9"),
    G_SHARP_AB_9(116, "G Sharp Ab 9"),
    A_9         (117, "A 9"),
    A_SHARP_BB_9(118, "A Sharp Bb 9"),
    B_9         (119, "B 9"),
    C_10         (120, "C 10"),
    C_SHARP_DB_10(121, "C Sharp Db 10"),
    D_10         (122, "D 10"),
    D_SHARP_EB_10(123, "D Sharp Eb 10"),
    E_10         (124, "E 10"),
    F_10         (125, "F 10"),
    F_SHARP_GB_10(126, "F Sharp Gb 10"),
    G_10         (127, "G 10");
    
    private final int ID;
    private final String NAME;

    private Notes(int ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }
    
    public static String getNoteName(int id) {
        return Stream.of(Notes.values())
                .filter(c -> c.getID() == id)
                .map(c -> c.getNAME())
                .findFirst()
                .orElse("Undefined");
    }
    
}
