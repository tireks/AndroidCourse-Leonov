package com.tirexmurina.facevolume.shared.data.local

import com.tirexmurina.facevolume.shared.domain.entity.Contact

object FakeContacts {
    val contacts: MutableList<Contact> = mutableListOf(
        Contact(
            id = 1,
            name = "Alice Johnson",
            email = "alice.johnson@example.com",
            phone = "+1 555 1234",
            note = "Met at a conference.",
            pic = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png",
            location = Contact.Location(
                country = "USA",
                city = "New York",
                address = "123 Broadway",
                timezone = Contact.Timezone.MSK_0
            )
        ),
        Contact(
            id = 2,
            name = "Bob Williams",
            email = "bob.williams@example.com",
            phone = "+1 555 2345",
            note = null,
            pic = null,
            location = Contact.Location(
                country = "USA",
                city = "Los Angeles",
                address = "456 Hollywood Blvd",
                timezone = null
            )
        ),
        Contact(
            id = 3,
            name = "Catherine Miller",
            email = null,
            phone = "+44 20 7946 0958",
            note = "Important client.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "UK",
                city = "London",
                address = "789 Baker Street",
                timezone = Contact.Timezone.MSK_2
            )
        ),
        Contact(
            id = 4,
            name = "David Brown",
            email = "david.brown@example.com",
            phone = null,
            note = "Follow up next week.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = null
        ),
        Contact(
            id = 5,
            name = "Eva Green",
            email = "eva.green@example.com",
            phone = "+49 30 123456",
            note = null,
            pic = null,
            location = Contact.Location(
                country = "Germany",
                city = "Berlin",
                address = "Alexanderplatz 1",
                timezone = null
            )
        ),
        Contact(
            id = 6,
            name = "Frank Harris",
            email = null,
            phone = null,
            note = "Loyal customer.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "Canada",
                city = "Toronto",
                address = "Queen Street West",
                timezone = Contact.Timezone.MSK_8
            )
        ),
        Contact(
            id = 7,
            name = "Grace Lee",
            email = "grace.lee@example.com",
            phone = "+81 3-1234-5678",
            note = "Interested in new products.",
            pic = null,
            location = null
        ),
        Contact(
            id = 8,
            name = "Henry Martin",
            email = "henry.martin@example.com",
            phone = "+1 555 6789",
            note = "Sent proposal.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = null
        ),
        Contact(
            id = 9,
            name = "Isabella Moore",
            email = null,
            phone = "+33 1 23456789",
            note = "VIP client.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "France",
                city = "Paris",
                address = "Champs-Élysées",
                timezone = Contact.Timezone.MSK_B12
            )
        ),
        Contact(
            id = 10,
            name = "Jack Davis",
            email = "jack.davis@example.com",
            phone = null,
            note = null,
            pic = null,
            location = Contact.Location(
                country = "Australia",
                city = "Sydney",
                address = "Circular Quay",
                timezone = Contact.Timezone.MSK_0
            )
        ),
        Contact(
            id = 11,
            name = "Karen Wilson",
            email = "karen.wilson@example.com",
            phone = "+1 555 9876",
            note = "Met at networking event.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "USA",
                city = "Chicago",
                address = "Lake Shore Drive",
                timezone = null
            )
        ),
        Contact(
            id = 12,
            name = "Leo Anderson",
            email = null,
            phone = "+49 89 123456",
            note = null,
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "Germany",
                city = "Munich",
                address = "Marienplatz",
                timezone = Contact.Timezone.MSK_B2
            )
        ),
        Contact(
            id = 13,
            name = "Mia Thompson",
            email = "mia.thompson@example.com",
            phone = "+44 20 7654321",
            note = "Potential partner.",
            pic = null,
            location = Contact.Location(
                country = "UK",
                city = "Manchester",
                address = null,
                timezone = Contact.Timezone.MSK_0
            )
        ),
        Contact(
            id = 14,
            name = "Noah White",
            email = "noah.white@example.com",
            phone = "+1 555 3456",
            note = "Follow up regarding proposal.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "USA",
                city = "San Francisco",
                address = "Market Street",
                timezone = null
            )
        ),
        Contact(
            id = 15,
            name = "Olivia Martinez",
            email = null,
            phone = null,
            note = null,
            pic = null,
            location = null
        ),
        Contact(
            id = 16,
            name = "Peter Clark",
            email = "peter.clark@example.com",
            phone = "+1 555 4321",
            note = "Long-term prospect.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = null
        ),
        Contact(
            id = 17,
            name = "Quinn Lewis",
            email = "quinn.lewis@example.com",
            phone = "+1 555 8765",
            note = "Referred by a friend.",
            pic = null,
            location = Contact.Location(
                country = "USA",
                city = "Seattle",
                address = "Pike Place Market",
                timezone = Contact.Timezone.MSK_B2
            )
        ),
        Contact(
            id = 18,
            name = "Rachel Walker",
            email = null,
            phone = "+61 2 12345678",
            note = "Interested in premium services.",
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = Contact.Location(
                country = "Australia",
                city = "Melbourne",
                address = "Flinders Street",
                timezone = Contact.Timezone.MSK_B3
            )
        ),
        Contact(
            id = 19,
            name = "Samuel King",
            email = "samuel.king@example.com",
            phone = null,
            note = null,
            pic = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1742566230~exp=1742569830~hmac=457e4f73a60401a497efba5b9c4a4a926e1af8951b55a070006636bc7864c575&w=826",
            location = null
        ),
        Contact(
            id = 20,
            name = "Tina Scott",
            email = "tina.scott@example.com",
            phone = "+1 555 6543",
            note = "Needs follow-up in two weeks.",
            pic = null,
            location = Contact.Location(
                country = "USA",
                city = "Miami",
                address = "Ocean Drive",
                timezone = Contact.Timezone.MSK_B6
            )
        )
    )
}