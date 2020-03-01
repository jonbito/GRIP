import GlobalNavigation from "@atlaskit/global-navigation";
import {JiraIcon} from "@atlaskit/logo";
import React, {Component, Fragment} from "react";
import ProfileDropdown from "./ProfileDropdown";
import HelpDropdown from "./HelpDropdown";
import DrawerContent from "./DrawerContent";
import CrossIcon from '@atlaskit/icon/glyph/cross';
import Modal, {ModalTransition} from "@atlaskit/modal-dialog";
import SettingsDrawer from "./SettingsDrawer";

export default class extends Component {
    state = {
        isCreateModalOpen: false,
        isSearchDrawerOpen: false,
        isSettingsDrawerOpen: false,
        isNotificationDrawerOpen: false,
        isStarredDrawerOpen: false
    };

    openCreateModal = () => this.setState({ isCreateModalOpen: true });
    closeCreateModal = () => this.setState({ isCreateModalOpen: false });

    openSearchDrawer = () => this.setState({ isSearchDrawerOpen: true });
    closeSearchDrawer = () => this.setState({ isSearchDrawerOpen: false });

    openSettingsDrawer = () => this.setState({isSettingsDrawerOpen: true});
    closeSettingsDrawer = () => this.setState({isSettingsDrawerOpen: false});

    openNotificationDrawer = () => this.setState({isNotificationDrawerOpen: true});
    closeNotificationDrawer = () => this.setState({isNotificationDrawerOpen: false});

    openStarredDrawer = () => this.setState({isStarredDrawerOpen: true});
    closeStarredDrawer = () => this.setState({isStarredDrawerOpen: false});

    renderSettingsDrawerContent = () => (
        <DrawerContent
            drawerTitle="Settings drawer"
            drawerBody="Can be controlled by passing the onSettingsClick prop"
        />
    );

    renderNotificationDrawerContents = () => (
        <DrawerContent
            drawerTitle="Notification drawer"
            drawerBody="You can toggle between a search drawer and the search modal"
        />
    );

    renderStarredDrawerContents = () => (
        <DrawerContent
            drawerTitle="Starred drawer"
            drawerBody="You can toggle between a search drawer and the search modal"
        />
    );

    render() {
        return (
            <Fragment>
                <GlobalNavigation
                    productIcon={() => <JiraIcon size="medium" />}
                    productHref="/"
                    // starred
                    onStarredClick={this.openStarredDrawer}
                    onStarredDrawerClose={this.closeStarredDrawer}
                    starredDrawerContents={this.renderStarredDrawerContents}
                    isStarredDrawerOpen={this.state.isStarredDrawerOpen}
                    // search
                    searchTooltip="Search (/)"
                    onSearchClick={this.openSearchDrawer}
                    onSearchDrawerClose={this.closeSearchDrawer}
                    searchDrawerContents={this.renderSearchDrawerContents}
                    isSearchDrawerOpen={this.state.isSearchDrawerOpen}
                    // create
                    onCreateClick={this.openCreateModal}
                    // notifications
                    onNotificationClick={this.openNotificationDrawer}
                    onNotificationDrawerClose={this.closeNotificationDrawer}
                    notificationDrawerContents={this.renderNotificationDrawerContents}
                    isNotificationDrawerOpen={this.state.isNotificationDrawerOpen}
                    // help
                    helpItems={HelpDropdown}
                    // settings
                    isSettingsDrawerOpen={this.state.isSettingsDrawerOpen}
                    settingsDrawerContents={() => (<SettingsDrawer />)}
                    onSettingsClick={this.openSettingsDrawer}
                    onSettingsDrawerClose={this.closeSettingsDrawer}
                    // profile
                    profileIconUrl="https://ui-avatars.com/api/?name=John+Doe&size=48&background=008759&bold=true&color=ffffff"
                    profileTooltip="Your profile"
                    profileItems={ProfileDropdown}

                    drawerBackIcon={CrossIcon}
                />
                <ModalTransition>
                    {this.state.isCreateModalOpen && (
                        <Modal
                            actions={[
                                { text: 'Create', onClick: this.closeCreateModal },
                                { text: 'Cancel', onClick: this.closeCreateModal, appearance: 'link' }
                            ]}
                            width="large"
                            onClose={this.closeCreateModal}
                            heading="Create issue"
                        >
                            Yay
                        </Modal>
                    )}
                </ModalTransition>
            </Fragment>
        )
    }
}
