var databaseData = Vue.resource('/messages{/id}');


Vue.component('messageForm', {
    props: ['messages'],
    data: function() {
            return {
                text: ''
            }
        },
    template: '<div>' +
        '<input type="text" placeholder="Write smth" v-model = "text" />' +
        '<input type="button" value="Save" @click = "save"/>' +
        '</div>',
    methods: {
        save: function () {
            var message = {text: this.text};

            databaseData.save({}, message).then(result =>
                result.json().then(data => {
                        this.messages.push(data);
                        this.text = "";
                    }));
        }
        
    }
});
Vue.component('listOfMessages', {
    props: ['messages'],
    template:   '<div>' +
        '<messageForm :messages = "messages" />' +
        '<messageRow v-for = "message in messages" :key = "message.id" :message = "message"></messageRow>' +
        '</div>',
    created: function () {
        databaseData.get().then(result =>
            result.json().then(data =>
                data.forEach(message =>
                    this.messages.push(message))));
    },
    methods: {
        editMessage: function () {

        }
    }

});
Vue.component('messageRow', {
    props: ['message'],
    template:   '<div>' +
                    '<i>({{  message.id  }})</i>   {{  message.text  }}' +
                        '<span>' +
                            '<input type="button" value="Edit" @click = "edit" />' +
                        '</span>' +
                '</div>',
    methods: {
        edit: function () {

        }
    }
});



var app = new Vue({
    el: '#app',
    template: '<listOfMessages :messages = "messages" />',
    data: {
        messages: [

        ]
    }
});