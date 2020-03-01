Account subclass: CheckingAccount["Class representing a checking account for a bank account"
    init: passNumber customer: passCustomer balance: passBalance["init class"
        number:=passNumber.
        customer:=passCustomer.
        balance:=passBalance
    ]

    accrue: rate[]
]