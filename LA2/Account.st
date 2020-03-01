Object subclass: Account["class Account. "

    |number customer balance|"Declaring 3 variables"

    Account class["constructor Account"
        new: n customer: c balance: b["Create Object"
            ^self new init: n customer: c balance: b
        ]
    ]

    init: n customer:c balance:b[
        "^self subclassResponsibility"
        number:=n.
        customer:=c.
        balance:=b
    ]

    accrue: rate ["Abstract"
        ^self subclassResponsibility
    ]

    balance[
        ^balance
    ]

    deposit: amount[
        balance:=balance+amount
    ]

    withdraw: amount["withdraw method in Account"
        balance:=balance-amount
    ]

    asString ["toString"
		^number asString,':',customer asString,':', balance asString
	]

]
