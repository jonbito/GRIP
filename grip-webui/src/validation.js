import {extend, setInteractionMode} from './util/vee-validate';
import {Rules} from './util/vee-validate';
import client from "./client";

setInteractionMode('input');

extend('min', {
    ...Rules.min,
    message: '{_field_} must be greater than {length} characters'
});

extend('max', {
    ...Rules.max,
    message: '{_field_} may not be greater than {length} characters'
});

extend('alpha_num', {
    ...Rules.alpha_num,
    message: '{_field_} must contain only alphanumeric characters'
});

extend('numeric', {
    ...Rules.numeric,
    message: '{_field_} must be numeric'
});

extend('email', {
    ...Rules.email,
    message: 'Email must be valid'
});

extend('required', {
    ...Rules.required,
    message: '{_field_} is required'
});

extend('starts_with_letter', {
    validate(v) {
        return !Number.isInteger(+v.charAt(0));
    },
    message: '{_field_} must start with a letter'
});

extend('project_key_available', {
    validate: (text, {groupId}) => {
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

extend('alpha_num_dash_space', {
    validate: text => /^[0-9A-Z-\s]*$/i.test(text),
    message: 'The name \'{_value_}\' may only contain alphanumeric characters or single hyphens'
})

extend('org_url_available', {
    validate: text => {
        return client.get('/org/urlExists/' + text).then(response => {
            return {
                valid: !response.data
            };
        }).catch(() => {
            return {
                valid: false
            };
        });
    },
    message: 'The name \'{_value_}\' is already taken'
})

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
