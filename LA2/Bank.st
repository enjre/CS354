Object subclass: Bank["class that represents the Bank. descendant of Object"

    |accounts| "array of accounts"

    Bank class["constructor for Bank"
        new["n is the customer name"
            ^super new init "order of st calls"
        ]
    ]

    init ["initialize new accounts constructor"
        accounts:= Set new
    ]

    add: account[
        accounts add: account "adds an account to the bank" 
    ]

    accrue: rate["accrue method with a parameter rate"
        accounts do: [:account | account accrue: rate]"for each loop of each existing account: "
    ]

    asString [
        |r| r:=''. "String r= "
        accounts do: [:account | r:=r, account asString, (Character nl asString)].
        ^r
    ]

]

"main method"
|thebank| thebank:=Bank new. "Creating new bank object then initializing"
|cust| cust:=Customer new: 'Ann'. "new Customer object "
thebank add: (CheckingAccount new: '01001' customer: cust balance: 100.00)."method to create new checking and savings accounts"
thebank add: (SavingsAccount new: '01002' customer: cust balance: 200.00).
thebank accrue: 0.02.
thebank asString printNl