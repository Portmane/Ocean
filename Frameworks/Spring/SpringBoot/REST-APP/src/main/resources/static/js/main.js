function getIndexFromListById(listOfSmth, id) {
    for (var i = 0; i < listOfSmth.length; i++) {
        if (listOfSmth[i] === id) {
            return i;
        }
    }

    return -1;
}

var databaseData = Vue.resource('/messages{/id}');


Vue.component('messageForm', {
    props: ['messages', 'objOfEditableMessage'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        objOfEditableMessage: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="Write smth" v-model = "text" />' +
        '<input type="button" value="Save" @click = "save"/>' +
        '</div>',
    methods: {
        save: function () {
            var message = {text: this.text};

            if (this.id) {
                databaseData.update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        var indexInListWitchWillBeChanged = getIndexFromListById(this.messages, data.id);
                        this.messages.splice(indexInListWitchWillBeChanged, 1, data);


                        this.id = '';
                        this.message = '';
                    }));
            } else {
                databaseData.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);


                        this.text = '';
                    }));
            }
        }

    }
});
Vue.component('listOfMessages', {
    props: ['messages'],
    data: function () {
        return {
            message: null
        }
    },
    template: '<div>' +
        '<messageForm :messages = "messages" :objOfEditableMessage = "message" />' +
        '<messageRow v-for = "message in messages" :key = "message.id" :message = "message" :editMethod = "editMethod"></messageRow>' +
        '</div>',
    created: function () {
        databaseData.get().then(result =>
            result.json().then(data =>
                data.forEach(message =>
                    this.messages.push(message))));
    },
    methods: {
        editMethod: function (message) {
            this.message = message;
        }
    }

});
Vue.component('messageRow', {
    props: ['message', 'editMethod'],
    template: '<div>' +
        '<i>({{  message.id  }})</i>   {{  message.text  }}' +
        '<span>' +
        '<input type="button" value="Edit" @click = "edit" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        }
    }
});


var app = new Vue({
    el: '#app',
    template: '<listOfMessages :messages = "messages" />',
    data: {
        messages: []
    }
});