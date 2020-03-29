import {extend, setInteractionMode} from 'vee-validate';
import {alpha_num, email, max, min, required} from 'vee-validate/dist/rules';
import client from "./client";

setInteractionMode('eager');

extend('min', {
    ...min,
    message: '{_field_} must be greater than {length} characters'
});

extend('max', {
    ...max,
    message: '{_field_} may not be greater than {length} characters'
});

extend('alpha_num', {
    ...alpha_num,
    message: '{_field_} must contain only alphanumeric characters'
});

extend('email', {
    ...email,
    message: 'Email must be valid'
});

extend('required', {
    ...required,
    message: '{_field_} is required'
});

extend('starts_with_letter', {
    validate(v) {
        return !Number.isInteger(+v.charAt(0));
    },
    message: '{_field_} must start with a letter'
});

extend('project_key_available', {
    validate: (text, { groupId }) => {
        const groupIdText = groupId ? '?groupId=' + groupId : '';
        return client.get('/project/keyExists/' + text + groupIdText).then(response => {
            return {
                valid: !response.data
            }
        }).catch(() => {
            return {
                valid: false
            }
        });
    },
    params: ['groupId'],
    message: 'Project key is already taken'
});

extend('username_available', {
    validate: text => {
        return client.get('/account/usernameExists/' + text).then((response) => {
            return {
                valid: !response.data
            };
        }).catch(() => {
            return {
                valid: false
            };
        })
    },
    message: 'Username is already taken'
})
