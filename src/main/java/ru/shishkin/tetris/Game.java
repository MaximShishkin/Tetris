package ru.shishkin.tetris;

public class Game {
    public int[][] mas = new int[30][20];
    public int napr = 0, fnapr;
    public int kol, kol_pol;
    public boolean endg;
    public boolean fprov = true;
    public int color = 0;
    public int[][] mass;
    public int[][] mas1 = {{1}, {1}, {1}, {1}, {1}};
    public int[][] mas2 = {{1, 1, 1}, {1, 0, 1}};
    public int[][] mas3 = {{1, 1}, {1, 0}};
    public int[][] mas4 = {{0, 1, 0}, {1, 1, 1}};
    public int[][] mas5 = {{1, 0, 0}, {1, 1, 1}, {0, 0, 1}};
    public int[][] mas6 = {{1, 1}, {1, 1}, {1, 1}};
    public int[][] mas7 = {{1, 0}, {1, 1}, {1, 0}, {1, 0}};
    public int row;
    public int columns;

    private int gX, gY;

    private void make_new() {
        int numbermas = (int) (Math.random() * 7);
        if (numbermas == 0) mass = mas1;
        if (numbermas == 1) mass = mas2;
        if (numbermas == 2) mass = mas3;
        if (numbermas == 3) mass = mas4;
        if (numbermas == 4) mass = mas5;
        if (numbermas == 5) mass = mas6;
        if (numbermas == 6) mass = mas7;
        color = (int) (Math.random() * 7);

        row = mass.length;
        columns = mass[0].length;

        gX = 10;
        gY = 0;
        boolean flagproverki = true;

        for (int i = gY; i < row; i++) {
            for (int j = gX; j < gX + columns; j++) {
                if (mas[i][j] != 0) flagproverki = false;
            }
        }

        if (flagproverki) {
            for (int i = gY; i < row; i++) {
                System.arraycopy(mass[i], 0, mas[i], gX, columns);
            }
        } else {
            endg = true;
        }
    }

    public void start() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                mas[i][j] = 0;
            }
        }

        kol = 0;
        make_new();
        endg = false;
    }

    public boolean peremGolova() {
        fprov = true;

        if (napr == 0) {
            if ((gY + row) < 30) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < columns; j++) {
                        if ((mas[gY + i][gX + j] == 1) && (mas[gY + i + 1][gX + j] > 1)) {
                            fprov = false;
                        }
                    }
                }

                if (fprov) {
                    gY++;
                }
            } else {
                gY = 30 - row;
                fprov = false;
            }
        }

        fnapr = 0;

        if (napr == 3) {
            boolean prov = true;
            if (gX - 1 >= 0) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < columns; j++) {
                        if ((mas[gY + i][gX + j] == 1) && (mas[gY + i][gX + j - 1] > 1)) {
                            prov = false;
                        }
                    }
                }

                if (prov) {
                    gX--;
                }
            }
        }

        if (napr == 2) {
            boolean prov = true;
            if (gX + columns <= 19) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < columns; j++) {
                        if ((mas[gY + i][gX + j] == 1) && (mas[gY + i][gX + j + 1] > 1)) {
                            prov = false;
                        }
                    }
                }
                if (prov) {
                    gX++;
                }
            }
        }

        if (napr == 5) {
            boolean prov = true;
            if (gY < 26 - row) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < columns; j++) {
                        if ((mas[gY + i][gX + j] == 1) && ((mas[gY + i + 3][gX + j] > 1) || (mas[gY + i + 2][gX + j] > 1) || (mas[gY + i + 1][gX + j] > 1))) {
                            prov = false;
                        }
                    }
                }

                if (prov) {
                    gY += 3;
                }
            }
        }

        if (napr == 1) {
            boolean fpovorot = true;
            if ((gY < 30 - columns) && (gX < 20 - row)) {
                for (int i = gY; i < gY + columns; i++) {
                    for (int j = gX; j < gX + row; j++) {
                        if (mas[i][j] > 1) {
                            fpovorot = false;
                        }
                    }
                }
            } else {
                fpovorot = false;
            }

            if (fpovorot) {
                int[][] masp = new int[columns][row];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < columns; j++) {
                        masp[j][i] = mass[i][j];
                    }
                }

                mass = masp;
                row = mass.length;
                columns = mass[0].length;

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < columns / 2; j++) {
                        int t = mass[i][j];
                        mass[i][j] = mass[i][columns - j - 1];
                        mass[i][columns - j - 1] = t;
                    }
                }
            }
        }

        napr = 0;
        return fprov;
    }

    public void perem() {
        boolean flag = peremGolova();

        if (flag) {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 20; j++) {
                    if (mas[i][j] == 1) {
                        mas[i][j] = 0;
                    }
                }
            }
            for (int i = gY; i < gY + row; i++) {
                for (int j = gX; j < gX + columns; j++) {
                    if (mass[i - gY][j - gX] == 1) {
                        mas[i][j] = mass[i - gY][j - gX];
                    }
                }
            }
        }

        if (flag == false) {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 20; j++) {
                    if (mas[i][j] == 1) {
                        mas[i][j] = 0;
                    }
                }
            }
            for (int i = gY; i < gY + row; i++) {
                for (int j = gX; j < gX + columns; j++) {
                    if (mass[i - gY][j - gX] == 1) {
                        mas[i][j] = mass[i - gY][j - gX];
                    }
                }
            }

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 20; j++) {
                    if (mas[i][j] == 1) {
                        if (color == 0) mas[i][j] = 2;
                        if (color == 1) mas[i][j] = 3;
                        if (color == 2) mas[i][j] = 4;
                        if (color == 3) mas[i][j] = 5;
                        if (color == 4) mas[i][j] = 6;
                        if (color == 5) mas[i][j] = 7;
                        if (color == 6) mas[i][j] = 8;
                    }
                }
            }
        }

        if (flag == false) {
            for (int i = 0; i <= 29; i++) {
                kol_pol = 0;
                for (int j = 0; j < 20; j++) {
                    if (mas[i][j] > 1) {
                        kol_pol = kol_pol + 1;
                    }
                }
                if (kol_pol == 20) {
                    for (int j = 0; j < 20; j++) {
                        for (int k = i; k >= 1; k--) {
                            mas[k][j] = mas[k - 1][j];
                        }

                        mas[0][j] = 0;
                    }

                    kol += 50;
                }
            }

            kol += 10;
            make_new();
        }
    }
}
