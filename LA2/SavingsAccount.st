Account subclass: SavingsAccount["Class representing a savings account"

    |interest|
        
        init: passNumber customer: passCustomer balance: passBalance[ "pass in 3 arguments: number, customer, balance. get interest"
        number:=passNumber.
        customer:=passCustomer.
        balance:=passBalance.
        interest:=0.0
        ]
    

    accrue: rate [
        balance:=balance+(balance*rate).
        interest:=interest+(balance*rate)
    ]

]