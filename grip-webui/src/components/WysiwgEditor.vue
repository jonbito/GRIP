<template>
    <div>
        <tiptap-vuetify
                :value="value"
                @input="(val) => $emit('input', val)"
                :extensions="extensions"
                @init="(editor) => $emit('init', editor)"
        />
    </div>
</template>

<script>
    import {
        Blockquote,
        Bold,
        BulletList,
        Code,
        HardBreak,
        Heading,
        History,
        HorizontalRule,
        Italic,
        Link,
        ListItem,
        OrderedList,
        Paragraph,
        Strike,
        TiptapVuetify,
        Underline
    } from 'tiptap-vuetify'

    export default {
        components: {TiptapVuetify},
        props: ['value'],
        data: () => ({
            extensions: [
                History,
                Blockquote,
                Link,
                Underline,
                Strike,
                Italic,
                ListItem,
                BulletList,
                OrderedList,
                [Heading, {
                    options: {
                        levels: [1, 2, 3]
                    }
                }],
                Bold,
                Code,
                HorizontalRule,
                Paragraph,
                HardBreak
            ]
        })
    }
</script>
