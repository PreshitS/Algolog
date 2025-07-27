package com.Algolog.algolog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Leaderboard {
    private int rank;
    private int userId;
    private String userName;

    private int easyCount;
    private int mediumCount;
    private int hardCount;

    private int totalScore;
}
