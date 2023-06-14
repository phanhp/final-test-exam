package mvc.service;

import org.springframework.stereotype.Service;

@Service
public class MyCode {
    //Method bị code rất ngu
    public boolean isSearchSuccess(String searchValue, String string) {
        System.out.println("search: "+searchValue );
        System.out.println("in string: "+string);
        String finalRepeat = "";
        String str1 = searchValue.replaceAll(" ","");
        String str2 = string.replaceAll(" ","");
        //searchValue độ dài = 0 hay string độ dài =0, trả về false
        if (searchValue.length() == 0 | string.length() == 0) {
            System.out.println("search length: "+searchValue.length());
            System.out.println("string length: "+string.length());
            System.out.println("*******************************  Final value of method is  *******************************");
            System.out.println("search: "+searchValue);
            System.out.println("in string: "+string);
            System.out.println("Final repeat: "+finalRepeat);
            System.out.println("---");
            return false;
        }
        String [] a = str1.split("");
        String[] b = str2.split("");
        String [] theShorter = new String[]{};
        String [] theLonger = new String[]{};
        if(a.length<=b.length){
            theShorter = a.clone();
            theLonger = b.clone();
        }else {
            theShorter = b.clone();
            theLonger =a.clone();
        }
        //Lỡ code ngu ở đây, nhác sửa lại :))
        String[] s1 = theShorter;
        String[] s2 = theLonger;
        System.out.println("search length: "+a.length);
        System.out.println("string length: "+b.length);

        double goodCheckRate = 0.75;
//        //searchvalue có 10 ký tự nhưng string có 7 ký tự, checkRate=0.7 sẽ false vì lớn hơn tiêu chuẩn
//        // nhưng nếu searchvalue có 9 ký tự, string có 7 ký tự, checkRate =0.78 thì vẫn tiếp tục thực hiện
//        if(Double.valueOf(string.length())/Double.valueOf(searchValue.length())<goodCheckRate){
//            return false;
//        }
        double badCheck = 0;
        int checkPoint = 0;

        for (int i = 0; i < s1.length; i++) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Current index of search = "+i);
            System.out.println("Current value of badCheck = "+badCheck);
            double curentCheckRate1 =  badCheck / Double.valueOf(s1.length);
            double curentCheckRate2 = badCheck / Double.valueOf(s2.length);
            System.out.println("bad check rate 1: "+curentCheckRate1);
            System.out.println("bad check rate 2: "+curentCheckRate2);
            System.out.println("curent check point: "+checkPoint);
            int curentOfString = s2.length-checkPoint;
            System.out.println("curent length of string to search: "+curentOfString);
            //Nếu badCheck/độ dài của string > goodCheckRate thì trả về false, không thì tiếp tục check tiếp
            //Nếu badcheck/độ dài của search > goodCheckRate thì trả về false, không thì tiếp tục check tiếp
            if (badCheck / Double.valueOf(s2.length) > goodCheckRate | badCheck / Double.valueOf(s1.length) > goodCheckRate) {
                double b1 = badCheck/Double.valueOf(s1.length);
                double b2 = badCheck/Double.valueOf(s2.length);
                System.out.println("goodCheckRate ="+goodCheckRate);
                System.out.println("badCheck/s1.length = "+b1);
                System.out.println("badCheck/s2.length = "+b2);
                System.out.println("*******************************  Final value of method is  *******************************");
                System.out.println("search: "+searchValue);
                System.out.println("in string: "+string);
                System.out.println("Final repeat: "+finalRepeat);
                System.out.println("---");
                return false;
            }

            // checkPoint lớn hơn hoặc bằng s2.length-1 thì break luôn vòng lặp và trả về true
            if (checkPoint == s2.length - 1) {
                System.out.println("checkPoint to return true: "+checkPoint);
                break;
            }

            // không thì sẽ tiếp tục search tại checkPoint tiếp theo
            //Tạo inLoopCheck để check tương quan giữa ký tự hiện tại của search và ký tự còn lại trong string
            //Nếu inLoopCheck = s2.length - checkpoint nghĩa là ký tự hiện tại của search không khớp với bất cứ ký tự nào còn
            //lại trong string thì badcheck sẽ tăng giá trị
            double inLoopCheck = 0;
            System.out.println("curent value of search: "+s1[i]);
            for (int j = checkPoint; j < s2.length; j++) {
                System.out.println("------");
                if (s1[i].equalsIgnoreCase(s2[j])) {
                    //Tìm ở đâu giống nhau sẽ dừng ở đó trong string rồi nhảy đến vòng lặp kế tiếp, checkPoint = j+1 được lưu
                    //Vòng lặp kế tiếp kiểm tra ký tự tiếp theo của searchValue tại checkpoint của string và
                    // checkPoint chính là vị trí sau của ký tự tìm thấy trước đó
                    System.out.println("curent value of string: "+s2[j]+" it's true");
                    finalRepeat = finalRepeat+s1[i];
                    System.out.println("curent finalRepeat: "+finalRepeat);
                    checkPoint = j + 1;
                    break;
                } else {
                    //Nếu không thì vòng lặp tiếp theo và inLoopCheck tăng giá trị
                    System.out.println("fail");
                    inLoopCheck++;
                }
            }
            //Nếu inLoopCheck >= khoảng check còn lại thì badCheck tăng giá trị
            if (inLoopCheck >= s2.length - checkPoint) {
                double b1 = s2.length- checkPoint;
                System.out.println("s2.length - checkPoint = "+b1);
                System.out.println("check fail so badcheck raised it value = "+badCheck+" + 1");
                badCheck++;
            }
        }
        System.out.println("*******************************  Final value of method is  *******************************");
        System.out.println("search: "+searchValue);
        System.out.println("in string: "+string);
        System.out.println("Final repeat: "+finalRepeat);
        System.out.println("---");
        return true;
    }
}
