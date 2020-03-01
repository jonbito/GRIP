import PropTypes from 'prop-types';
import React, {Component} from "react";

export default class DrawerContent extends Component {
    render() {
        return (
            <div>
                <h1>{this.props.drawerTitle}</h1>
                <div>{this.props.drawerBody}</div>
            </div>
        );
    }
}

DrawerContent.propTypes = {
    drawerTitle: PropTypes.string,
    drawerBody: PropTypes.string,
};


