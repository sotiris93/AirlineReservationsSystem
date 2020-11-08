public class MoneyChange {
        private double amount_charged;
        private double amount_given;
        private double change;

        public MoneyChange(double amount_charged, double amount_given)
        {
            this.amount_charged = amount_charged;
            this.amount_given = amount_given;
        }
        public double get_change(double amount_charged, double amount_given){
            System.out.print("\nYou inserted "+amount_given+" into the machine\n");
            change = amount_given - amount_charged;
            System.out.print("Your change is: "+change+" euros\n");
            return change;
        }

        public void print_change(){
            double cents_1= 0.0;
            double cents_2= 0.0;
            double cents_5= 0.0;
            double cents_10= 0.0;
            double cents_20=0.0;
            double cents_50=0.0;
            double euro_1=0.0;
            double euro_2=0.0;
            double euro_5=0.0;
            double euro_10=0.0;
            double euro_20=0.0;
            double euro_50=0.0;

            double money = change;

            while(money >0){

                try{
                    if(money>= 50){
                        euro_50 = money / 50;
                        money = money % 50;
                    }
                    else if(money < 50 && money >=20){
                        euro_20 = money / 20;
                        money = money % 20;
                    }
                    else if(money <20 && money>= 10){
                        euro_10 = money / 10;
                        money = money % 10;
                    }
                    else if(money <10 && money>= 5){
                        euro_5 = money / 5;
                        money = money % 5;
                    }
                    else if(money <5 && money>=2){
                        euro_2 = money / 2;
                        money = money %2;
                    }
                    else if(money <2 && money >=1){
                        euro_1 = money / 1;
                        money = money %1;
                    }
                    else if(money<1 && money >= 0.5){
                        cents_50 = money / 0.5;
                        money = money %0.5;
                    }
                    else if(money<0.5 && money >=0.2){
                        cents_20 = money / 0.2;
                        money = money % 0.2;
                    }
                    else if(money<0.2 && money >= 0.1){
                        cents_10 = money / 0.1;
                        money = money % 0.1;
                    }
                    else if(money<0.1 && money >= 0.05){
                        cents_5 = money / 0.05;
                        money = money % 0.05;
                    }
                    else if(money<0.05 && money>= 0.02){
                        cents_2 = money / 0.02;
                        money = money % 0.02;
                    }
                    else if(money<0.02 && money >=0.01){
                        cents_1 = money / 0.01;
                        money = money % 0.01;
                    }

                }catch(Exception e){
                    System.out.print(e);
                }
            }
            amount_charged = money;
            double[] array_money = new double[12];
            array_money[0] = euro_50;
            array_money[1] = euro_20;
            array_money[2] = euro_10;
            array_money[3] = euro_5;
            array_money[4] = euro_2;
            array_money[5] = euro_1;
            array_money[6] = cents_50;
            array_money[7] = cents_20;
            array_money[8] = cents_10;
            array_money[9] = cents_5;
            array_money[10] = cents_2;
            array_money[11] = cents_1;

            String[] money_name = {"50 euros", "20 euros", "10 euros", "5 euros", "2 euros", "1 euros",
                    "50 cents", "20 cents", "10 cents", "5 cents", "2 cents", "1 cent "};
            System.out.println();
            for(int i=0; i<money_name.length; i++){
                for(int j=0;j<1; j++){
                    System.out.println(" "+money_name[i] +"\t "+"  :  "+(int)array_money[i] +((i<4) ?  " bill(s)  " : " coin(s)"));
                }
            }
            System.out.println();
        }
    }


