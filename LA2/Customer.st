Object subclass: Customer["class representing a customer"

    |name|"Declare global variable 'name' "

    Customer class["constructor for customer"
        new: name2["new makes new variables or objects"
            ^self new init:name2"return this class's new initializing variables"
        ]
    ]

    init: name2["init the name"
        name:=name2
    ]

    asString [
        ^name "return the name"
    ]

]