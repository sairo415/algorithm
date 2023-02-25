package a20230225;

public class Solution_pr_lv0_옹알이1 {

	public int solution(String[] babbling) {
        int answer = 0;
        for(int i=0; i<babbling.length; i++){
            babbling[i] = babbling[i].replace("aya", "x");
            babbling[i] = babbling[i].replace("ye", "x");
            babbling[i] = babbling[i].replace("woo", "x");
            babbling[i] = babbling[i].replace("ma", "x");
            babbling[i] = babbling[i].replace("x", "");
            if(babbling[i].equals("")){
                answer++;
            }
        }
        return answer;
    }
}
