<template>
    <div>
        <slot :height="height"/>
    </div>
</template>

<script>
    export default {
        name: "StretchHeight",
        props: {
            offset: {
                required: false,
                type: Number,
                default: 0
            }
        },
        data: () => ({
            height: '0px'
        }),
        mounted() {
            this.calcDimensions();
            window.addEventListener('resize', this.calcDimensions);
        },
        destroyed() {
            window.removeEventListener('resize', this.calcDimensions);
        },
        methods: {
            async calcDimensions() {
                this.height = undefined;
                await this.$nextTick();

                var bodyRect = document.body.getBoundingClientRect(),
                    elemRect = this.$el.getBoundingClientRect(),
                    offset   = elemRect.top - bodyRect.top;
                this.height = (window.innerHeight - offset - this.offset) + 'px';
            }
        }
    }
</script>
