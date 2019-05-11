package ru.dsstaroverov.lunchMenu.util;

import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.to.VoteTo;

public class VoteUtil {
    public static Vote fromTo(VoteTo vote){
        return new Vote(vote.getId(),vote.getUserId(),vote.getMenuId());
    }

    public static VoteTo asTo(Vote vote){
        return new VoteTo(vote.getId(), vote.getUserId(),vote.getMenuId(),0);
    }
}
