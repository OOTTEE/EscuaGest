import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';
import 'antd/dist/antd.css'
import {Competition} from "./Competition";

export default {
  title: 'Competition/Competition',
  component: Competition,
  parameters: {
    layout: 'fullscreen',
  },
} as ComponentMeta<typeof Competition>;

const Template: ComponentStory<typeof Competition> = (args) => <Competition {...args} />;

export const Competition1 = Template.bind({});
Competition1.args = {
  competition_id: 1
};

