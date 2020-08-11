import React from 'react';
import {Menu, Layout} from "antd";
import {DownOutlined, QuestionCircleFilled, UserOutlined} from "@ant-design/icons";
import styled from "styled-components";
import {Link} from "react-router-dom";
import logo from "../../assets/logo.svg";
import keycloak from "../../keycloak";

const LogoContainer = styled(Link)`
  display: flex;
  align-items: center;
  width: 155px;
  
  img {
   height: 22px;
      margin-right: 8px;
  }
  
  span {
 position: relative;
  top: 2px;
  color: #fff;
  font-weight: 600;
  font-size: 24px;
  font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif; 
  }
`

export default ({history}) => {
    return (
        <Layout.Header style={{height: '56px', lineHeight: '56px'}}>
            <div style={{display: 'flex'}}>
                <LogoContainer to="/">
                    <img src={logo}/>
                    <span>Grip</span>
                </LogoContainer>
                <Menu theme="dark" mode="horizontal" triggerSubMenuAction="click" onSelect={(item) => {
                    history.push(item.key);
                }}>
                    <Menu.SubMenu title={(<span>Projects <DownOutlined style={{marginRight: 0}}/></span>)}>
                        <Menu.Item key="/projects">View All Projects</Menu.Item>
                        <Menu.Item key="/projects/create">Create Project</Menu.Item>
                    </Menu.SubMenu>
                    <Menu.Item key="/people">People</Menu.Item>
                </Menu>
                <div style={{flex: '2'}}/>
                <Menu theme="dark" mode="horizontal" triggerSubMenuAction="click" onSelect={(item) => {
                    if(item.key === '/logout') {
                        keycloak.logout();
                    } else {
                        history.push(item.key);
                    }
                }}>
                    <Menu.SubMenu style={{padding: '0 10px'}}
                                  title={<QuestionCircleFilled style={{marginRight: 0, fontSize: '18px'}}/>}>
                        <Menu.Item key="/logout">Logout</Menu.Item>
                    </Menu.SubMenu>
                    <Menu.SubMenu style={{padding: '0 10px'}}
                                  title={<UserOutlined style={{marginRight: 0, fontSize: '18px'}}/>}>
                        <Menu.Item key="/logout">Logout</Menu.Item>
                    </Menu.SubMenu>
                </Menu>
            </div>
        </Layout.Header>
    )
}