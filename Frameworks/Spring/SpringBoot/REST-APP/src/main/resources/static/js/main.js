var messageApi = Vue.resource('/messages{/id}');



Vue.component('message-row', {
    props: ['message'],
    template: '<div><i>({{  message.id  }})</i>   {{  message.text  }}</div>'
});

Vue.component('messages-list', {
    props: ['messages'],
    template:   '<div>' +
                    '<message-row v-for = "message in messages" :key = "message.id" :message = "message"></message-row>' +
                '</div>',
    created: function () {
        messageApi.get().then(result => console.log(result));
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages = "messages"/>',
    data: {
        messages: [

        ]
    }
});