//
//  main.c
//  hwhw
//
//  Created by 김철균 on 2018. 4. 8..
//  Copyright © 2018년 김대현. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void del(int n, char **arr, int num);

int main()
{
    char **bingo;
    int a;
    int n;
    int i, j, k, l, r;
    int t, num;
    int random[1000];
    printf("<BINGO>\n 행,열에 해당하는 숫자를 입력하시오: ");
    scanf("%d", &n);
    
    bingo = (int**)malloc(sizeof(int*)*n);
    for (a = 0; a < n; a++) {
        bingo[a] = (int*)malloc(sizeof(int)*n);
    }
    srand((unsigned)time(NULL));
    
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
        re1:;
            r = rand() % (n*n) + 1;
            for (l = 0; l <= i; l++) {
                for (k = 0; k < j; k++) {
                    if (r == bingo[l][k])
                        goto re1;
                }
            }
            bingo[i][j] = r;
        }
    }
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            printf("%5d", bingo[i][j]);
        }
        printf("\n");
    }
    for (t = 0; t < (n*n); t++) {
        printf("\n지울 숫자를 입력하세요: ");
        scanf("%d", &num);
        del(n, bingo, num);
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                printf("%5d", bingo[i][j]);
            }
            printf("\n");
        }
    }
}

void del(int n, char **arr, int num) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++); {
            if (arr[i][j] == num) {
                arr[i][j] = "*";
                break;
            }
        }
    }
}

